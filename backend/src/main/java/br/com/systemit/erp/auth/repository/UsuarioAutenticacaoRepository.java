package br.com.systemit.erp.auth.repository;

import br.com.systemit.erp.auth.model.Usuario;
import br.com.systemit.erp.auth.model.UsuarioAutenticacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioAutenticacaoRepository extends JpaRepository<UsuarioAutenticacao, Integer> {

    @Query(" SELECT o FROM UsuarioAutenticacao o " +
           " WHERE o.usuario.id = :idUsuario")
    List<UsuarioAutenticacao> findByUsuario(@Param("idUsuario") Integer idUsuario);
}
