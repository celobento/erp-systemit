package br.com.systemit.erp.auth.repository;

import br.com.systemit.erp.auth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByLogin(String login);
    Usuario findByEmail(String email);
}
