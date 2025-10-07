package br.com.aluguelcarros.api.rest.dto;

import br.com.aluguelcarros.api.domain.ContratoAluguel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Schema(description = "Payload para criar um Contrato de Aluguel")
public record ContratoAluguelRequest(

        @Schema(example = "2025-10-07")
        @NotNull
        LocalDate dataAssinatura,

        @Schema(example = "2500.00")
        @NotNull @Positive
        Double valorTotal,

        @Schema(example = "ATIVO")
        @NotNull
        ContratoAluguel.StatusContrato status,

        @Schema(example = "1", description = "ID do cliente que está alugando o veículo")
        @NotNull
        Long clienteId,

        @Schema(example = "1", description = "ID do automóvel alugado")
        @NotNull
        Long automovelId,

        @Schema(example = "1", description = "ID do banco envolvido (opcional)")
        Long bancoId
) {}
