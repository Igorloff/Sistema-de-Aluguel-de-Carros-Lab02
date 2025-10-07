package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de login simples")
public record LoginResponse(
        String tipoUsuario,
        Long id,
        String nome,
        String documento,
        String mensagem
) {}
