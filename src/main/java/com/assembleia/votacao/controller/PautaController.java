package com.assembleia.votacao.controller;

import com.assembleia.votacao.dto.PautaRequestDTO;
import com.assembleia.votacao.dto.PautaResponseDTO;
import com.assembleia.votacao.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Api(value = "pauta")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 86400) // SOMENTE PARA API DE TESTE LOCAL
@RequestMapping(value = "api/v1/pauta", produces = "application/json")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @ApiOperation(value="Obter todas as pautas", response = PautaResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pautas encontradas.")
    })
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.pautaService.listarPautas());
    }

    @ApiOperation(value="Obter pauta por id", response = PautaResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pauta encontrada.")
    })
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        return ResponseEntity.ok(this.pautaService.getPauta(id));
    }

    @ApiOperation(value="Criar uma pauta", response = PautaResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pauta criada com sucesso.")
    })
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PautaRequestDTO pauta) throws URISyntaxException {
        PautaResponseDTO response = this.pautaService.criarPauta(pauta);
        return ResponseEntity.created(new URI(response.getId().toString())).body(response);
    }
}

