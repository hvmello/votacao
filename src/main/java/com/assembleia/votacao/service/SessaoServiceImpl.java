package com.assembleia.votacao.service;

import com.assembleia.votacao.dto.*;
import com.assembleia.votacao.entity.Decisao;
import com.assembleia.votacao.entity.Pauta;
import com.assembleia.votacao.entity.Sessao;
import com.assembleia.votacao.entity.Voto;
import com.assembleia.votacao.exception.BusinessException;
import com.assembleia.votacao.exception.NotFoundException;
import com.assembleia.votacao.repository.PautaRepository;
import com.assembleia.votacao.repository.SessaoRepository;
import com.assembleia.votacao.repository.VotoRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SessaoServiceImpl implements SessaoService {

    private final VotoRepository votoRepository;
    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;
    private final Environment environment;
    private final CpfValidatorService cpfValidatorService;

    public SessaoServiceImpl(VotoRepository votoRepository, SessaoRepository sessaoRepository, PautaRepository pautaRepository, Environment environment, CpfValidatorService cpfValidatorService) {
        this.votoRepository = votoRepository;
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.environment = environment;
        this.cpfValidatorService = cpfValidatorService;
    }


    @Override
    public List<SessaoResponseDTO> listarSessoes() {
        List<Sessao> sessaoList = this.sessaoRepository.findAll();
        List<SessaoResponseDTO> listaSessaoResponse = new ArrayList<>();


        sessaoList.forEach(
                sessao -> {
                    final PautaResponseDTO pautaResponseDTO = new PautaResponseDTO(sessao.getPauta().getId(),
                            sessao.getPauta().getTitulo(), sessao.getPauta().getDescricao());

                    listaSessaoResponse.add(new SessaoResponseDTO(String.valueOf(sessao.getId()),
                            pautaResponseDTO,
                            sessao.getMinutosParaExpirarSessao(),
                            sessao.getDataHoraFimVotacaoPauta(),
                            sessao.getVotos(),
                            sessao.isFechada()));
                }
        );

        return listaSessaoResponse;
    }

    @Override
    public SessaoResponseDTO getSessao(String id) {

        if (id.chars().allMatch(Character::isDigit)) return null;

        Sessao sessao = this.sessaoRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("Sessão não encontrada."));

        final PautaResponseDTO pautaResponseDTO = new PautaResponseDTO(sessao.getPauta().getId(), sessao.getPauta().getTitulo(), sessao.getPauta().getDescricao());

        return new SessaoResponseDTO(String.valueOf(sessao.getId()),
                pautaResponseDTO,
                sessao.getMinutosParaExpirarSessao(),
                sessao.getDataHoraFimVotacaoPauta(),
                sessao.getVotos(),
                sessao.isFechada());
    }

    @Override
    public SessaoResponseDTO criarSessao(SessaoRequestDTO dto) {
        Pauta pauta = this.pautaRepository.findById(Long.valueOf(dto.getPautaId())).
                orElseThrow(() -> new NotFoundException("Pauta não encontrada."));

        Integer minutesToExpiration = dto.getMinutosParaFechamentoSessao();
        if (minutesToExpiration == null || minutesToExpiration <= 0)
            minutesToExpiration = (Integer.parseInt(Objects.requireNonNull(environment.getProperty("minutos.para.expirar.sessao.default"))));

        Sessao sessao = new Sessao(pauta, minutesToExpiration);
        sessao = this.sessaoRepository.save(sessao);

        final PautaResponseDTO pautaResponseDTO = new PautaResponseDTO(sessao.getPauta().getId(), sessao.getPauta().getTitulo(), sessao.getPauta().getDescricao());

        return new SessaoResponseDTO(String.valueOf(sessao.getId()),
                pautaResponseDTO,
                sessao.getMinutosParaExpirarSessao(),
                sessao.getDataHoraFimVotacaoPauta(),
                sessao.getVotos(),
                sessao.isFechada());
    }

    @Override
    public VotoResponseDTO adicionarVoto(VotoRequestDTO dto) {

        if (Objects.isNull(dto.getSessaoId()) || Objects.isNull(dto.getDecisao())) {
            throw new BusinessException("Os campos Id da Sessão e a decisão do voto são obrigatórios");
        }

        Sessao sessao = this.sessaoRepository.findById(dto.getSessaoId()).orElseThrow(() -> new NotFoundException("Sessão não encontrada."));

        final Decisao decisao = getDecisao(dto);

        validaVoto(sessao, dto);

        Voto vote = new Voto(dto.getCpf(), decisao);
        votoRepository.save(vote);
        sessao.addVote(vote);
        sessaoRepository.save(sessao);

        return new VotoResponseDTO(true);
    }

    private static Decisao getDecisao(VotoRequestDTO dto) {
        if (!dto.getDecisao().trim().isEmpty()) {
            try {

                return Decisao.valueOf(dto.getDecisao().trim().toUpperCase().replace("Ã", "A"));

            } catch (IllegalArgumentException e) {
                throw new BusinessException("O valor do voto deve ser 'Sim' ou 'Não'.");
            }
        } else {
            throw new NotFoundException("A descrição do voto informado não foi encontrado.");
        }


    }

    private void validaVoto(Sessao sessao, VotoRequestDTO dto) {

        if (!sessao.isFechada() && sessao.getDataHoraFimVotacaoPauta().isBefore(LocalDateTime.now())) {
            // PODEMOS FAZER A SESSAO SER FECHADA VIA SCHEDULE TAMBEM EM DETERMINADO TEMPO SER FECHADA
            sessao.setFechada(true);
        }
        // VALIDAR NESSA ORDEM CONSOME MENOS RECURSOS
        if (sessao.isFechada())
            throw new BusinessException("Votação encerrada.");

        if (sessao.isVotoDuplicado(dto.getCpf()))
            throw new BusinessException("A pessoa com este CPF já votou");

        if (!cpfValidatorService.isAbleToVote(dto.getCpf()))
            throw new BusinessException("CPF inserido não tem permissão para votar.");
    }

    @Override
    public ResultadoSessaoDTO getResultadoVotacao(String id) {
        Sessao sessao = this.sessaoRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("Sessão não encontrada."));

        if (!sessao.isFechada())
            throw new BusinessException("Votação permanece aberta, ainda não há um resultado disponível");

        List<Voto> votos = sessao.getVotos();

        final long votosSim = votos.stream().filter(voto -> voto.getDecisao().equals(Decisao.SIM)).count();
        long votosNao = (long) votos.size() - votosSim;
        if (votosNao < 0) votosNao *= -1;

        ResultadoSessaoDTO resultResponseDto = new ResultadoSessaoDTO();
        final PautaResponseDTO pautaResponseDTO = new PautaResponseDTO(sessao.getPauta().getId(), sessao.getPauta().getTitulo(), sessao.getPauta().getDescricao());
        resultResponseDto.setPauta(pautaResponseDTO);
        resultResponseDto.setVotosSim(votosSim);
        resultResponseDto.setVotosNao(votosNao);

        return resultResponseDto;
    }


}