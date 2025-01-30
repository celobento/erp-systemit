package br.com.systemit.erp.auth.service;

import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.repository.ModuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloService {

    private ModuloRepository repository;

    public ModuloService(ModuloRepository repository) {
        this.repository = repository;
    }

    public Modulo buscarPorId(Integer id){
        return repository.findById(id).orElse(null);
    }

    public List<Modulo> findAll() {
        return repository.findAll();
    }

}
