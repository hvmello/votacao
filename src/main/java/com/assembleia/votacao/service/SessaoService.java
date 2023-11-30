package com.assembleia.votacao.service;

import com.assembleia.votacao.dto.*;

import java.util.List;

public interface SessaoService {
    List<SessaoResponseDTO> listarSessoes();
    SessaoResponseDTO getSessao(String id);
    SessaoResponseDTO criarSessao(SessaoRequestDTO dto);
    VotoResponseDTO adicionarVoto(VotoRequestDTO dto);
    ResultadoSessaoDTO getResultadoVotacao(String id);
}