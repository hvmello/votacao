package com.assembleia.votacao.controller;

import com.assembleia.votacao.dto.SessaoRequestDTO;
import com.assembleia.votacao.dto.SessaoResponseDTO;
import com.assembleia.votacao.dto.VotoRequestDTO;
import com.assembleia.votacao.dto.VotoResponseDTO;
import com.assembleia.votacao.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Api(value = "sessao")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 86400) // SOMENTE PARA API DE TESTE LOCAL
@RequestMapping(value = "sessao", produces = "application/json")
public class SessaoController {

    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @Operation(summary = "Obter todas as Sessões")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sessões encontradas.")})
    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.sessaoService.listarSessoes());
    }

    @Operation(summary = "obter sessao por id", description = "sessao precisa existir")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sessão Encontrada.")})
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return ResponseEntity.ok(this.sessaoService.getSessao(id));
    }

    @Operation(summary = "Criar Sessão")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Sessão criada com sucesso.")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody SessaoRequestDTO voting) throws URISyntaxException {
        SessaoResponseDTO response = this.sessaoService.criarSessao(voting);
        return ResponseEntity.created(new URI(response.getId().toString())).body(response);
    }

    @Operation(summary = "Realizar Voto na Sessão")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Voto realizado."),})
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/votar")
    public ResponseEntity<?> votar(@RequestBody VotoRequestDTO vote) throws URISyntaxException {
        VotoResponseDTO response = this.sessaoService.adicionarVoto(vote);
        return ResponseEntity.created(new URI(response.toString())).body(response);
    }

    @Operation(summary = "Obter Resultado da Votação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resultado da Votação Encontrado."),
            @ApiResponse(code = 400, message = "ID inválido")
    })
    @GetMapping("/resultado/{id}")
    public ResponseEntity<?> getVotingResult(@PathVariable String id) {
        return ResponseEntity.ok(this.sessaoService.getResultadoVotacao(id));
    }

}
