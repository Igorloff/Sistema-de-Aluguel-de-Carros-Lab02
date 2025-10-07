package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Schema(description = "Payload para criar um Contrato de Crédito")
public record ContratoCreditoRequest(
        @Schema(example = "CC-2025-001")
        @NotBlank
        String numero,

        @Schema(example = "15000.00")
        @NotNull @Positive
        Double valorAprovado,

        @Schema(example = "2025-10-06")
        @NotNull
        LocalDate dataAprovacao,
        
        @Schema(description = "ID do banco que aprovou o crédito")
        @NotNull
        Long idBanco
) {}