package br.com.systemit.erp.auth.repository;

import br.com.systemit.erp.auth.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

    @Query(name = Perfil.FIND_ALL)
    List<Perfil> findAllOrdered();

    @Query(name = Perfil.FIND_BY_MODULO)
    List<Perfil> findByModulo(@Param("idModulo") Integer idModulo);

}
