package br.com.systemit.erp.auth.validator;

import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.repository.PerfilRepository;
import br.com.systemit.erp.exceptions.RegistroDuplicadoException;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class PerfilValidator {

    private PerfilRepository repository;

    public PerfilValidator(PerfilRepository repository) {
        this.repository = repository;
    }

    public void validar(Perfil perfil) {
        if (existePerfilCadastrado(perfil)){
            throw new RegistroDuplicadoException("Perfil já cadastrado");
        }
    }

    private boolean existePerfilCadastrado(Perfil perfil) {
        Optional<Perfil> perfilEncontrado = repository.findByNomeAndRole(perfil.getNome(), perfil.getRole());
        if (perfil.getId() == null) {
            return perfilEncontrado.isPresent();
        }

        return perfilEncontrado.isPresent() && !perfil.getId().equals(perfilEncontrado.get().getId());
    }
}
