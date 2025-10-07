package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(description = "Payload para registrar um novo Pedido de Aluguel")
public record PedidoAluguelRequest(
        @Schema(description = "ID do Cliente que está solicitando")
        @NotNull
        Long idCliente,

        @Schema(description = "ID do Automóvel desejado")
        @NotNull
        Long idAutomovel,

        @Schema(example = "2025-12-01")
        @NotNull @Future
        LocalDate dataInicio,

        @Schema(example = "2025-12-08")
        @NotNull @Future
        LocalDate dataFim
) {}