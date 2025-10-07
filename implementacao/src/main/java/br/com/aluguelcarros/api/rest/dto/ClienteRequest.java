package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Schema(description = "Payload para criar/atualizar cliente")
public record ClienteRequest(
        @Schema(example = "Ana Lima")
        @NotBlank @Size(min = 2, max = 120)
        String nome,

        @Schema(example = "12345678909")
        @CPF
        String cpf,

        @Schema(example = "MG1234567")
        @NotBlank
        String rg,

        @Schema(example = "Rua A, 123")
        @NotBlank @Size(min = 3, max = 200)
        String endereco,

        @Schema(example = "Engenheira")
        @NotBlank @Size(min = 2, max = 80)
        String profissao,

        @Schema(example = "7500.50")
        @NotNull @PositiveOrZero
        Double rendimento
) {}