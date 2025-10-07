package br.com.aluguelcarros.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id") // Chave para a herança
public class Cliente extends Usuario {

    @CPF
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank
    private String rg;

    @NotBlank
    @Size(min = 3, max = 200)
    private String endereco;

    @NotBlank
    @Size(min = 2, max = 80)
    private String profissao;

    @PositiveOrZero
    @Column(nullable = false)
    private Double rendimento;

    // Getters e Setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }
    public Double getRendimento() { return rendimento; }
    public void setRendimento(Double rendimento) { this.rendimento = rendimento; }
}