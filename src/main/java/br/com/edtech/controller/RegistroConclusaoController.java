package br.com.edtech.controller;

import br.com.edtech.model.registroConclusao.DataCreateConclusao;
import br.com.edtech.model.registroConclusao.DataGetConclusao;
import br.com.edtech.model.registroConclusao.RegistroConclusao;
import br.com.edtech.model.user.User;
import br.com.edtech.repository.TreinamentoRepository;
import br.com.edtech.service.RegistroConclusaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conclusao")
public class RegistroConclusaoController {
    private final RegistroConclusaoService registroConclusaoService;
    private final TreinamentoRepository treinamentoRepository;

    public RegistroConclusaoController(RegistroConclusaoService registroConclusaoService, TreinamentoRepository treinamentoRepository) {
        this.registroConclusaoService = registroConclusaoService;
        this.treinamentoRepository = treinamentoRepository;
    }

    @PostMapping
    public ResponseEntity registrarConclusao(@Valid
                                                 @RequestBody DataCreateConclusao data,
                                             @AuthenticationPrincipal User user) {
        try {
            var treinamento = treinamentoRepository.getReferenceById(data.treinamentoId());
            var conclusao = registroConclusaoService.registrarConclusao(data,treinamento, user);
            return ResponseEntity.ok().body("Conclusão do treinamento: '" + treinamento.getTitulo() + "' pelo usuário: " + user.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao registrar conclusão de treinamento.");
        }
    }
}
