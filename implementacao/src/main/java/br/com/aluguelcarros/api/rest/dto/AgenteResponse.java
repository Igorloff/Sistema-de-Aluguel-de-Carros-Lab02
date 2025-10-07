package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de um Agente")
public record AgenteResponse(
        Long id,
        String nome,
        String cnpj
) {}