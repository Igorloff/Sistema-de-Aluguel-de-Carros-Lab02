package br.com.aluguelcarros.api.repository;

import br.com.aluguelcarros.api.domain.PedidoAluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel, Long> {
}