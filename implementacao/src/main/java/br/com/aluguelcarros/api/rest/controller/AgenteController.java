package br.com.aluguelcarros.api.rest.controller;

import br.com.aluguelcarros.api.rest.dto.AgenteRequest;
import br.com.aluguelcarros.api.rest.dto.AgenteResponse;
import br.com.aluguelcarros.api.service.AgenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agentes")
@Tag(name = "Agentes", description = "CRUD de Agentes de análise")
public class AgenteController {

    private final AgenteService service;

    public AgenteController(AgenteService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar um novo agente")
    public ResponseEntity<AgenteResponse> criar(@Valid @RequestBody AgenteRequest req) {
        return ResponseEntity.status(201).body(service.criar(req));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agente por ID")
    public AgenteResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    @Operation(summary = "Listar todos os agentes")
    public List<AgenteResponse> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar agente existente")
    public AgenteResponse atualizar(@PathVariable Long id, @Valid @RequestBody AgenteRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover agente pelo ID")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}