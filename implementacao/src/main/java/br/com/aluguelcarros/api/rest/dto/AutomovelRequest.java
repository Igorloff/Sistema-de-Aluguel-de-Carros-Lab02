package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Payload para criar/atualizar um Automóvel")
public record AutomovelRequest(

        @Schema(example = "Toyota")
        @NotBlank @Size(max = 80)
        String marca,

        @Schema(example = "Corolla")
        @NotBlank @Size(max = 120)
        String modelo,

        @Schema(example = "BRA2E19")
        @NotBlank @Size(max = 7)
        String placa,
        
        @Schema(example = "1234567890")
        @NotBlank @Size(max = 100)
        String matricula,

        @Schema(example = "2023")
        @NotNull @Positive
        Integer ano
) {}