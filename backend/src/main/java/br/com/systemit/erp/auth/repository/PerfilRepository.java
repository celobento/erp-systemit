package br.com.systemit.erp.auth.repository;

import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

    @Query(name = Perfil.FIND_ALL)
    List<Perfil> findAllOrdered();

    @Query(name = Perfil.FIND_BY_MODULO)
    List<Perfil> findByModulo(@Param("idModulo") Integer idModulo);

    List<Perfil> findByNomeContainingIgnoreCase(String nome);

    List<Perfil> findByRoleContainingIgnoreCase(String role);

    List<Perfil> findByNomeAndRoleIgnoreCase(String nome, String role);

    Optional<Perfil> findByNomeAndRole(String nome, String role);

    boolean existsByNomeAndRole(String nome, String role);

    Page<Perfil> findByModulo(Modulo modulo, Pageable pageable);
}
