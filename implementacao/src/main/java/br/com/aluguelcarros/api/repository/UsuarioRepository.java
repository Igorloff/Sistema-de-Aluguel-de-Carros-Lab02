package br.com.aluguelcarros.api.repository;

import br.com.aluguelcarros.api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}