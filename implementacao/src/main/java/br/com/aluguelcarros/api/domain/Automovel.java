package br.com.aluguelcarros.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "automovel", uniqueConstraints = @UniqueConstraint(columnNames = "placa"))
public class Automovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 7, max = 7)
    @Column(nullable = false, unique = true, length = 7)
    private String placa;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String matricula;

    @NotNull
    @Column(nullable = false)
    private Integer ano;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String marca;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String modelo;

    @Column(nullable = false)
    private boolean disponivel = true;

    public Automovel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
