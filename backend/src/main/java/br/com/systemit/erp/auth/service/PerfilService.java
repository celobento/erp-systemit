package br.com.systemit.erp.auth.service;

import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.repository.PerfilRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private PerfilRepository repository;

    public PerfilService(PerfilRepository repository) {
        this.repository = repository;
    }

    public Perfil buscarPorId(Integer id){
        return repository.findById(id).orElse(null);
    }

}
