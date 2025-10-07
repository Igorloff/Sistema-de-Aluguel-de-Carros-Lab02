package br.com.aluguelcarros.api.rest.dto;

import br.com.aluguelcarros.api.domain.ContratoAluguel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Schema(description = "Payload para criar um Contrato de Aluguel")
public record ContratoAluguelRequest(
        @NotNull
        LocalDate dataAssinatura,

        @NotNull @Positive
        Double valorTotal,

        @NotNull
        ContratoAluguel.StatusContrato status
) {}