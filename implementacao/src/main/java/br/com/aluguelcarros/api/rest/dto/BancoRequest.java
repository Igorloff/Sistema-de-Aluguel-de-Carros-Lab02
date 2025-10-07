package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

@Schema(description = "Payload para criar/atualizar um Banco")
public record BancoRequest(
        @Schema(example = "Banco Digital de Crédito S.A.")
        @NotBlank
        @Size(min = 2, max = 120)
        String nome,

        @Schema(example = "11222333000181")
        @CNPJ
        String cnpj
) {}