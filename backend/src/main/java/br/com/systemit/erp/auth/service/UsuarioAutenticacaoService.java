package br.com.systemit.erp.auth.service;

import br.com.systemit.erp.auth.model.Usuario;
import br.com.systemit.erp.auth.model.UsuarioAutenticacao;
import br.com.systemit.erp.auth.repository.UsuarioAutenticacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioAutenticacaoService {

    private UsuarioAutenticacaoRepository repository;

    public UsuarioAutenticacaoService(UsuarioAutenticacaoRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioAutenticacao> buscaByUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario.getId());
    }
}
