package br.com.aluguelcarros.api.rest.controller;

import br.com.aluguelcarros.api.rest.dto.ContratoAluguelRequest;
import br.com.aluguelcarros.api.rest.dto.ContratoAluguelResponse;
import br.com.aluguelcarros.api.service.ContratoAluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contratos-aluguel")
@Tag(name = "Contratos de Aluguel", description = "Gerenciamento de Contratos de Aluguel")
public class ContratoAluguelController {

    private final ContratoAluguelService service;

    public ContratoAluguelController(ContratoAluguelService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar um novo contrato de aluguel")
    public ResponseEntity<ContratoAluguelResponse> criar(@Valid @RequestBody ContratoAluguelRequest req) {
        return ResponseEntity.status(201).body(service.criar(req));
    }

    @GetMapping
    @Operation(summary = "Listar todos os contratos de aluguel")
    public List<ContratoAluguelResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contrato de aluguel por ID")
    public ContratoAluguelResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}