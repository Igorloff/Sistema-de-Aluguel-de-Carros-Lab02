package br.com.aluguelcarros.api.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pedido_aluguel")
public class PedidoAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "automovel_id", nullable = false)
    private Automovel automovel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contrato_credito_id", referencedColumnName = "id")
    private ContratoCredito contratoCredito;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contrato_aluguel_id", referencedColumnName = "id")
    private ContratoAluguel contratoAluguel;
    
    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusPedido status;

    public enum StatusPedido {
        SOLICITADO,
        EM_ANALISE_CREDITO,
        CREDITO_APROVADO,
        CREDITO_REPROVADO,
        CONTRATO_GERADO,
        CANCELADO
    }

    // --- GETTERS E SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }

    public ContratoCredito getContratoCredito() {
        return contratoCredito;
    }

    public void setContratoCredito(ContratoCredito contratoCredito) {
        this.contratoCredito = contratoCredito;
    }

    public ContratoAluguel getContratoAluguel() {
        return contratoAluguel;
    }

    public void setContratoAluguel(ContratoAluguel contratoAluguel) {
        this.contratoAluguel = contratoAluguel;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}