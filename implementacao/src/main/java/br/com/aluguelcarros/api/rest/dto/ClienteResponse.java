package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de cliente")
public record ClienteResponse(
        Long id,
        String nome,
        String cpf,
        String rg,
        String endereco,
        String profissao,
        Double rendimento
) {}