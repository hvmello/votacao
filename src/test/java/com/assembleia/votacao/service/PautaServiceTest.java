package com.assembleia.votacao.service;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.assembleia.votacao.dto.PautaRequestDTO;
import com.assembleia.votacao.dto.PautaResponseDTO;
import com.assembleia.votacao.entity.Pauta;
import com.assembleia.votacao.repository.PautaRepository;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Mock
    private PautaRepository pautaRepository;


    @Test
    void deveCriarPautaComSucesso() {
        PautaRequestDTO pautaRequestDTO = new PautaRequestDTO();
        pautaRequestDTO.setTitulo("Aumento de verba");
        pautaRequestDTO.setDescricao("null");

        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaRequestDTO.getTitulo());
        pauta.setDescricao(pautaRequestDTO.getDescricao());

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        PautaResponseDTO pautaCreated = pautaService.criarPauta(pautaRequestDTO);

        assertNotNull(pautaCreated);
        assertEquals("Aumento de verba", pautaCreated.getTitulo());
        verify(pautaRepository).save(any(Pauta.class));
    }
}