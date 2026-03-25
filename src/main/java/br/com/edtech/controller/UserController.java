package br.com.edtech.controller;

import br.com.edtech.exception.BusinessRuleException;
import br.com.edtech.model.user.*;
import br.com.edtech.service.TokenService;
import br.com.edtech.service.UserServise;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServise userServise;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public UserController(UserServise userServise, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userServise = userServise;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

//    Coletar meus dados
    @GetMapping("/my-details")
    public ResponseEntity<DataGetUser> getUserDetails(@AuthenticationPrincipal User data) {
        var user = userServise.getMyDetails(data);
        return ResponseEntity.ok(new DataGetUser(user));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody DataCreateUser data) {
        var user = userServise.create(data);
        try {
            var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = authenticationManager.authenticate(token);
            String tokenAccess = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new DataGetUserAndToken(user, tokenAccess));
        } catch (Exception e) {
            System.out.println("Erro na criação de usuário: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody DataLogin data) {
        if (data.email() == null || data.email().isEmpty()) {
            throw new BusinessRuleException("O e-mail é obrigatório");
        }
        if (data.password() == null || data.password().isEmpty()) {
            throw new BusinessRuleException("Senha está errada.");
        }

        try {
            var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var authentication = authenticationManager.authenticate(token);
            String tokenAccess = tokenService.generateToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new DataGetUserAndToken((User) authentication.getPrincipal(), tokenAccess));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro no Login: "  + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/update-user")
    public ResponseEntity updateUser(@Valid @RequestBody DataUpdateUser newUser, @AuthenticationPrincipal User data) {
        var user = userServise.updateUser(newUser, data);
        return ResponseEntity.ok(new DataGetUser(user));
    }

    @Transactional
    @DeleteMapping("/desative-user")
    public ResponseEntity desativeteUser(@AuthenticationPrincipal User data) {
        userServise.desativeteUser(data);
        return ResponseEntity.ok().body("USUÁRIO: " + data.getName() + " DESATIVADO.");
    }
}
