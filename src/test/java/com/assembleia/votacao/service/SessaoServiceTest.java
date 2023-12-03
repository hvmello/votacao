package com.assembleia.votacao.service;

import com.assembleia.votacao.dto.SessaoResponseDTO;
import com.assembleia.votacao.entity.Pauta;
import com.assembleia.votacao.entity.Sessao;
import com.assembleia.votacao.repository.SessaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class SessaoServiceTest {

    @Mock
    public SessaoRepository sessaoRepository;
    @InjectMocks
    public SessaoServiceImpl sessaoService;

    @Test
    public void shouldReturnAgendas() {
        List<Sessao> sessoes = new ArrayList<>();
        Pauta pauta = new Pauta("Aquisição da indústria Moreira empreendimentos",
                "Devemos comprar a indústria mediante os dados apresentados?");

        sessoes.add(new Sessao(pauta, 5));
        sessoes.add(new Sessao(pauta, 10));

        Mockito.when(sessaoRepository.findAll()).thenReturn(sessoes);

        List<SessaoResponseDTO> resp = sessaoService.listarSessoes();
        assertEquals(2, resp.size());
    }

}