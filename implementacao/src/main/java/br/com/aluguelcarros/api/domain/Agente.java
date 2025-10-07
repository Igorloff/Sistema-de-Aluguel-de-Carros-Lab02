package br.com.aluguelcarros.api.domain;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "agente")
@PrimaryKeyJoinColumn(name = "id")
public class Agente extends Usuario {

    @CNPJ
    @Column(unique = true, nullable = false, length = 14)
    private String cnpj;

    // Getters e Setters
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}