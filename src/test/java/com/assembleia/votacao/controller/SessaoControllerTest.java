package com.assembleia.votacao.controller;

import com.assembleia.votacao.service.SessaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SessaoControllerTest {

    @Mock
    public SessaoService sessaoService;

    @InjectMocks
    public SessaoController sessaoController;

    @Test
    public void deveRetornarZeroSessoes() {
        ResponseEntity<?> resp = sessaoController.getAll();

        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        assertEquals(0, ((LinkedList<?>) Objects.requireNonNull(resp.getBody())).size());
    }

}