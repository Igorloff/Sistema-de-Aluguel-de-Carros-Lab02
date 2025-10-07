package br.com.aluguelcarros.api.rest.controller;

import br.com.aluguelcarros.api.rest.dto.AutomovelRequest;
import br.com.aluguelcarros.api.rest.dto.AutomovelResponse;
import br.com.aluguelcarros.api.service.AutomovelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/automoveis")
@Tag(name = "Automóveis", description = "CRUD de Automóveis")
public class AutomovelController {

    private final AutomovelService service;

    public AutomovelController(AutomovelService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar um novo automóvel")
    public ResponseEntity<AutomovelResponse> criar(@Valid @RequestBody AutomovelRequest req) {
        return ResponseEntity.status(201).body(service.criar(req));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar automóvel por ID")
    public AutomovelResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    @Operation(summary = "Listar todos os automóveis")
    public List<AutomovelResponse> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar automóvel existente")
    public AutomovelResponse atualizar(@PathVariable Long id, @Valid @RequestBody AutomovelRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover automóvel pelo ID")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}