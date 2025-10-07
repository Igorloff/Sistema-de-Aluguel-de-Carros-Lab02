package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Requisição de login simples")
public record LoginRequest(
        @Schema(example = "Ana Lima")
        @NotBlank String nome,

        @Schema(example = "12345678909", description = "Pode ser CPF (cliente) ou CNPJ (agente)")
        @NotBlank String documento
) {}
