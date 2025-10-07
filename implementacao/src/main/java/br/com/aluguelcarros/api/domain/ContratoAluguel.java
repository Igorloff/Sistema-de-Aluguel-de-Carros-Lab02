package br.com.aluguelcarros.api.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contrato_aluguel")
public class ContratoAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataAssinatura;

    @Column(nullable = false)
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusContrato status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "automovel_id", nullable = false)
    private Automovel automovel;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco; // opcional — só se houver contrato de crédito

    public enum StatusContrato {
        ATIVO,
        ENCERRADO
    }

    public ContratoAluguel() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataAssinatura() { return dataAssinatura; }
    public void setDataAssinatura(LocalDate dataAssinatura) { this.dataAssinatura = dataAssinatura; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public StatusContrato getStatus() { return status; }
    public void setStatus(StatusContrato status) { this.status = status; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Automovel getAutomovel() { return automovel; }
    public void setAutomovel(Automovel automovel) { this.automovel = automovel; }

    public Banco getBanco() { return banco; }
    public void setBanco(Banco banco) { this.banco = banco; }
}
