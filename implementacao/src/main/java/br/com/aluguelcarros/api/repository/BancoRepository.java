package br.com.aluguelcarros.api.repository;

import br.com.aluguelcarros.api.domain.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    boolean existsByCnpj(String cnpj);
}