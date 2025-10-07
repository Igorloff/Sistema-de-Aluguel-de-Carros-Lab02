package br.com.aluguelcarros.api.rest.controller;

import br.com.aluguelcarros.api.rest.dto.LoginRequest;
import br.com.aluguelcarros.api.rest.dto.LoginResponse;
import br.com.aluguelcarros.api.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@Tag(name = "Login", description = "Autenticação simples de usuários")
public class LoginController {

    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Efetuar login simples (nome + CPF/CNPJ)")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        try {
            return ResponseEntity.ok(service.autenticar(req));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
