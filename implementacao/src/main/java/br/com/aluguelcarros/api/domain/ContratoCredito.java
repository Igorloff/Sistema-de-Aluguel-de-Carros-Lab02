package br.com.aluguelcarros.api.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contrato_credito")
public class ContratoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numero;

    @Column(nullable = false)
    private Double valorAprovado;

    @Column(nullable = false)
    private LocalDate dataAprovacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banco_id", nullable = false)
    private Banco banco;

    // --- GETTERS E SETTERS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getValorAprovado() {
        return valorAprovado;
    }

    public void setValorAprovado(Double valorAprovado) {
        this.valorAprovado = valorAprovado;
    }

    public LocalDate getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(LocalDate dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}