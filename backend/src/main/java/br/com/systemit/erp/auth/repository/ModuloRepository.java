package br.com.systemit.erp.auth.repository;

import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuloRepository extends JpaRepository<Modulo, Integer> {

    @Override
    @Query(" SELECT o FROM Modulo o " +
            " order by o.nome")
    List<Modulo> findAll();

}
