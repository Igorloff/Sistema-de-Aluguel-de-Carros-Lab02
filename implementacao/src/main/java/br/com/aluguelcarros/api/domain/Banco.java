package br.com.aluguelcarros.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "banco")
@PrimaryKeyJoinColumn(name = "id")
public class Banco extends Agente {

}