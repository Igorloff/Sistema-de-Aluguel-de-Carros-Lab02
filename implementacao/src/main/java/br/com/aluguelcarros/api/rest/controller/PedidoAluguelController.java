package br.com.aluguelcarros.api.rest.controller;

import br.com.aluguelcarros.api.rest.dto.PedidoAluguelRequest;
import br.com.aluguelcarros.api.rest.dto.PedidoAluguelResponse;
import br.com.aluguelcarros.api.service.PedidoAluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos-aluguel")
@Tag(name = "Pedidos de Aluguel", description = "Gerenciamento do fluxo de Pedidos de Aluguel")
public class PedidoAluguelController {

    private final PedidoAluguelService service;

    public PedidoAluguelController(PedidoAluguelService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Registrar um novo pedido de aluguel (Passo 1)")
    public ResponseEntity<PedidoAluguelResponse> registrarPedido(@Valid @RequestBody PedidoAluguelRequest req) {
        return ResponseEntity.status(201).body(service.registrarPedido(req));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar um pedido de aluguel completo por ID")
    public PedidoAluguelResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os pedidos de aluguel")
    public List<PedidoAluguelResponse> listar() {
        return service.listar();
    }
}