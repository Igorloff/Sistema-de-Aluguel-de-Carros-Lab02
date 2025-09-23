package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de cliente")
public record ClienteResponse(
        Long id,
        String cpf,
        String rg,
        String nome,
        String endereco,
        String profissao
) {}
