package br.com.aluguelcarros.api.rest.dto;

import br.com.aluguelcarros.api.domain.ContratoAluguel;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Representação de um Contrato de Aluguel")
public record ContratoAluguelResponse(
        Long id,
        LocalDate dataAssinatura,
        Double valorTotal,
        ContratoAluguel.StatusContrato status
) {}