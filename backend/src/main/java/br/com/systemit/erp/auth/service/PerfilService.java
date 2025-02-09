package br.com.systemit.erp.auth.service;

import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.repository.PerfilRepository;
import br.com.systemit.erp.auth.validator.PerfilValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository repository;
    private final PerfilValidator validator;

    public Optional<Perfil> buscarPorId(Integer id){
        return repository.findById(id);
    }

    public List<Perfil> findAll() {
        return repository.findAllOrdered();
    }

    public List<Perfil> buscarPorModulo(Integer id){
        return repository.findByModulo(id);
    }

    public Perfil salvar(Perfil perfil) {
        validator.validar(perfil);
        return repository.save(perfil);
    }

    public void atualizar(Perfil perfil) {
        if (perfil.getId() == null) {
            throw new IllegalArgumentException("Perfil não cadastrado ainda");
        }
        repository.save(perfil);
    }

    public void deletar(Perfil perfil) {
        repository.delete(perfil);
    }

    public List<Perfil> pesquisar(String nome, String role) {
        if((nome != null && !nome.isEmpty()) &&
                (role != null && !role.isEmpty())) {
            return repository.findByNomeAndRoleIgnoreCase(nome, role);
        }

        if(nome != null && !nome.isEmpty()) {
            return repository.findByNomeContainingIgnoreCase(nome);
        }

        if(role != null && !role.isEmpty()) {
            return repository.findByRoleContainingIgnoreCase(role);
        }

        return repository.findAll();

    }
    public List<Perfil> pesquisaByExample(String nome, String role) {
        Perfil perfil = new Perfil();
        perfil.setNome(nome);
        perfil.setRole(role);
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Perfil> perfilExample = Example.of(perfil, matcher);
        return repository.findAll(perfilExample);
    }

}
