package br.com.aluguelcarros.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "banco")
@PrimaryKeyJoinColumn(name = "id")
public class Banco extends Agente {

    @Column(nullable = false)
    private String tipo = "BANCO";

    public Banco() {}

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
