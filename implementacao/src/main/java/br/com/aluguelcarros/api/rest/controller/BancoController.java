package br.com.aluguelcarros.api.rest.controller;

import br.com.aluguelcarros.api.rest.dto.BancoRequest;
import br.com.aluguelcarros.api.rest.dto.BancoResponse;
import br.com.aluguelcarros.api.service.BancoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bancos")
@Tag(name = "Bancos", description = "CRUD de Bancos parceiros")
public class BancoController {

    private final BancoService service;

    public BancoController(BancoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar um novo banco")
    public ResponseEntity<BancoResponse> criar(@Valid @RequestBody BancoRequest req) {
        return ResponseEntity.status(201).body(service.criar(req));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar banco por ID")
    public BancoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    @Operation(summary = "Listar todos os bancos")
    public List<BancoResponse> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar banco existente")
    public BancoResponse atualizar(@PathVariable Long id, @Valid @RequestBody BancoRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover banco pelo ID")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}