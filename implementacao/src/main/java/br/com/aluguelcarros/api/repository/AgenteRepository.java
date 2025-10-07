package br.com.aluguelcarros.api.repository;

import br.com.aluguelcarros.api.domain.Agente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenteRepository extends JpaRepository<Agente, Long> {
    boolean existsByCnpj(String cnpj);
}