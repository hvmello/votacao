package com.assembleia.votacao.service;

import com.assembleia.votacao.entity.Pauta;
import com.assembleia.votacao.exception.NotFoundException;
import com.assembleia.votacao.dto.PautaRequestDTO;
import com.assembleia.votacao.dto.PautaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assembleia.votacao.repository.PautaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public List<PautaResponseDTO> listarPautas() {
        List<Pauta> pautaList = this.pautaRepository.findAll();

        return pautaList.stream().map(
                pauta -> new PautaResponseDTO(pauta.getPautaId(), pauta.getTitulo(), pauta.getDescricao())
        ).collect(Collectors.toList());
    }

    @Override
    public PautaResponseDTO getPauta(String id) {
        Pauta pauta = this.pautaRepository.findById(Long.valueOf(id)).
                orElseThrow(() -> new NotFoundException("Pauta não encontrada."));

        return new PautaResponseDTO(pauta.getPautaId(), pauta.getTitulo(), pauta.getDescricao());
    }

    @Override
    public PautaResponseDTO criarPauta(PautaRequestDTO dto) {
        Pauta pauta = new Pauta(dto.getTitulo(), dto.getDescricao());
        pauta = pautaRepository.save(pauta);

        return new PautaResponseDTO(pauta.getPautaId(), pauta.getTitulo(), pauta.getDescricao());
    }
}