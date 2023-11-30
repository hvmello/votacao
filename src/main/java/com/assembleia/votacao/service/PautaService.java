package com.assembleia.votacao.service;



import com.assembleia.votacao.dto.PautaRequestDTO;
import com.assembleia.votacao.dto.PautaResponseDTO;

import java.util.List;

public interface PautaService {
    List<PautaResponseDTO> listarPautas();
    PautaResponseDTO getPauta(String id);
    PautaResponseDTO criarPauta(PautaRequestDTO dto);
}
