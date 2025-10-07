package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

@Schema(description = "Payload para criar/atualizar um Agente")
public record AgenteRequest(
        @Schema(example = "Empresa de Análise de Crédito S.A.")
        @NotBlank
        @Size(min = 2, max = 120)
        String nome,

        @Schema(example = "12345678000190")
        @CNPJ
        String cnpj
) {}