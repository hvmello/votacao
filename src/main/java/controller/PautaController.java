package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PautaService;

@RestController
@RequestMapping(value = "api/v1/voting", produces = "application/json")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }


}

