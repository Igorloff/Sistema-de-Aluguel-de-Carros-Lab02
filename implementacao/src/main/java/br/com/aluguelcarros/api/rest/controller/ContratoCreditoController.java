package br.com.aluguelcarros.api.rest.controller;

import br.com.aluguelcarros.api.rest.dto.ContratoCreditoRequest;
import br.com.aluguelcarros.api.rest.dto.ContratoCreditoResponse;
import br.com.aluguelcarros.api.service.ContratoCreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contratos-credito")
@Tag(name = "Contratos de Crédito", description = "Gerenciamento de Contratos de Crédito")
public class ContratoCreditoController {

    private final ContratoCreditoService service;

    public ContratoCreditoController(ContratoCreditoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar um novo contrato de crédito")
    public ResponseEntity<ContratoCreditoResponse> criar(@Valid @RequestBody ContratoCreditoRequest req) {
        return ResponseEntity.status(201).body(service.criar(req));
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os contratos de crédito")
    public List<ContratoCreditoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contrato de crédito por ID")
    public ContratoCreditoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}