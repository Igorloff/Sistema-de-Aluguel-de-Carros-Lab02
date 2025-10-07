package br.com.aluguelcarros.api.repository;

import br.com.aluguelcarros.api.domain.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
    boolean existsByPlaca(String placa);
}