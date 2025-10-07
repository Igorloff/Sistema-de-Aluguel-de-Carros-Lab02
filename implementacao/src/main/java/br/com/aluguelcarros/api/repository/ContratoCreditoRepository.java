package br.com.aluguelcarros.api.repository;

import br.com.aluguelcarros.api.domain.ContratoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoCreditoRepository extends JpaRepository<ContratoCredito, Long> {
}