package br.com.aluguelcarros.api.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contrato_aluguel")
public class ContratoAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataAssinatura;
    
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusContrato status;

    public enum StatusContrato {
        ATIVO,
        ENCERRADO
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(LocalDate dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusContrato getStatus() {
        return status;
    }

    public void setStatus(StatusContrato status) {
        this.status = status;
    }
}