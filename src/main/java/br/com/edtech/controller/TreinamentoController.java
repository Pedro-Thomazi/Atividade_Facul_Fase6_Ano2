package br.com.edtech.controller;

import br.com.edtech.model.treinamento.DataCreateTreinamento;
import br.com.edtech.model.treinamento.DataGetTreinamento;
import br.com.edtech.model.treinamento.Treinamento;
import br.com.edtech.model.user.User;
import br.com.edtech.service.TreinamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treinamento")
public class TreinamentoController {
    private final TreinamentoService treinamentoService;

    public TreinamentoController(TreinamentoService treinamentoService) {
        this.treinamentoService = treinamentoService;
    }

    @PostMapping("/create")
    public ResponseEntity createTreinamentio(
            @Valid
            @RequestBody DataCreateTreinamento data
    ) {
        var treinamento = treinamentoService.create(data);
        return ResponseEntity.ok(new DataGetTreinamento(treinamento));
    }
}
