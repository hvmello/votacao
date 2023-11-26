package service;



import dto.PautaRequestDTO;
import dto.PautaResponseDTO;

import java.util.List;

public interface PautaService {
    List<PautaResponseDTO> listPautas();
    PautaResponseDTO getPauta(String id);
    PautaResponseDTO createPauta(PautaRequestDTO dto);
}
