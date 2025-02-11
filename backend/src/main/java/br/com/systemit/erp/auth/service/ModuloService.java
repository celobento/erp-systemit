package br.com.systemit.erp.auth.service;

import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.repository.ModuloRepository;
import br.com.systemit.erp.auth.repository.specs.ModuloSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
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
    public void deletar(Modulo modulo) {
        repository.delete(modulo);
    };

    public List<Modulo> pesquisa(Integer id, String nome) {
//        Specification<Modulo> specs = Specification
//                .where(ModuloSpecs.idEqual(id))
//                .and(ModuloSpecs.nomeLike(nome));

        Specification<Modulo> specs = Specification
                .where((root, query, cb) -> cb.conjunction());
                if (id != null) {
                    specs = specs.and(ModuloSpecs.idEqual(id));
                }
                if (nome != null) {
                    specs = specs.and(ModuloSpecs.nomeLike(nome));
                }
        return repository.findAll(specs);
    }

}
