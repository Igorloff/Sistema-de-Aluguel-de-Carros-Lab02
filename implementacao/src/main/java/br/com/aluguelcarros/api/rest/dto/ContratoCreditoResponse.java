package br.com.aluguelcarros.api.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Representação de um Contrato de Crédito")
public record ContratoCreditoResponse(
        Long id,
        String numero,
        Double valorAprovado,
        LocalDate dataAprovacao,
        BancoResponse banco
) {}