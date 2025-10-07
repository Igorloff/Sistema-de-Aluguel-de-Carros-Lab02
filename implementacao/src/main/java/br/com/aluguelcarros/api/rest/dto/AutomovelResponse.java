package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representação de um Automóvel")
public record AutomovelResponse(
        Long id,
        String marca,
        String modelo,
        String placa,
        String matricula,
        Integer ano,
        boolean disponivel
) {}