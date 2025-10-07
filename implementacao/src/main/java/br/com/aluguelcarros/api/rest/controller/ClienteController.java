package br.com.aluguelcarros.api.rest.controller;

import br.com.aluguelcarros.api.rest.dto.ClienteRequest;
import br.com.aluguelcarros.api.rest.dto.ClienteResponse;
import br.com.aluguelcarros.api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "CRUD de clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar um novo cliente")
    public ResponseEntity<ClienteResponse> criar(@Valid @RequestBody ClienteRequest req) {
        return ResponseEntity.status(201).body(service.criar(req));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public List<ClienteResponse> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente existente")
    public ClienteResponse atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover cliente pelo ID")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}