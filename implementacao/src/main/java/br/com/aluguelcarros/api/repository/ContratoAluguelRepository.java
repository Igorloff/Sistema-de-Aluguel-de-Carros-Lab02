package br.com.aluguelcarros.api.repository;

import br.com.aluguelcarros.api.domain.ContratoAluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoAluguelRepository extends JpaRepository<ContratoAluguel, Long> {
}