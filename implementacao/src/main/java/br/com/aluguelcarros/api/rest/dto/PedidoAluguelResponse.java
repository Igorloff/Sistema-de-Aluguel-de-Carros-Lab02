package br.com.aluguelcarros.api.rest.dto;

import br.com.aluguelcarros.api.domain.PedidoAluguel;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Representação completa de um Pedido de Aluguel")
@JsonInclude(JsonInclude.Include.NON_NULL) // Não mostra campos nulos (ex: contrato se ainda não existe)
public record PedidoAluguelResponse(
        Long id,
        PedidoAluguel.StatusPedido status,
        LocalDate dataInicio,
        LocalDate dataFim,
        ClienteResponse cliente,
        AutomovelResponse automovel,
        ContratoCreditoResponse contratoCredito,
        ContratoAluguelResponse contratoAluguel
) {}