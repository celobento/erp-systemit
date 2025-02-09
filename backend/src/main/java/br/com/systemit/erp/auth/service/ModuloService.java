package br.com.systemit.erp.auth.service;

import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.repository.ModuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloService {

    private ModuloRepository repository;

    public ModuloService(ModuloRepository repository) {
        this.repository = repository;
    }

    public Optional<Modulo> buscarPorId(Integer id){
        return repository.findById(id);
    }

    public List<Modulo> findAll() {
        return repository.findAll();
    }

}
