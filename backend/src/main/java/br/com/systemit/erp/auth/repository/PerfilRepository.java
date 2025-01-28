package br.com.systemit.erp.auth.repository;

import br.com.systemit.erp.auth.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
}
