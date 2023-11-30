package com.assembleia.votacao.service;

import com.assembleia.votacao.exception.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CpfValidatorService {
    private final RestTemplate restTemplate;
    private final Environment environment;

    @Autowired
    public CpfValidatorService(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    public boolean isAbleToVote(String cpf) {
        try {
            String url = environment.getProperty("cpf.service.url") + cpf;
            String ABLE_TO_VOTE = "ABLE_TO_VOTE";

            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            return Objects.equals(response.getBody(), ABLE_TO_VOTE);

        } catch (HttpStatusCodeException ex) {
            throw new IntegrationException("Erro ao buscar permissão para votar de CPF.");
        }
    }

}
