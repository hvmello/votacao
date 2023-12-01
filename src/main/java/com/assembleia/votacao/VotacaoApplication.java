package com.assembleia.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VotacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotacaoApplication.class, args);
    }

    @RequestMapping("/")
    public String home(){
        return "API Votação!";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
