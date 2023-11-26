package service;

import dto.VotacaoRequestDTO;
import dto.VotacaoResponseDTO;

import java.util.List;

public interface VotacaoService {
    List<VotacaoResponseDTO> listVotings();
    VotacaoResponseDTO getVoting(String id);
    VotacaoResponseDTO createVoting(VotacaoRequestDTO dto);
    VotacaoResponseDTO addVote(VotacaoRequestDTO dto);
    VotacaoResponseDTO getVotingResult(String id);
}