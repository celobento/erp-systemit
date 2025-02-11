package br.com.systemit.erp.auth.security;

import br.com.systemit.erp.auth.model.Usuario;
import br.com.systemit.erp.auth.model.UsuarioAutenticacao;
import br.com.systemit.erp.auth.service.UsuarioAutenticacaoService;
import br.com.systemit.erp.auth.service.UsuarioService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class CustomAuthentication implements Authentication {

    private final Usuario usuario;
    private final List<UsuarioAutenticacao> perfis;
//    private final UsuarioService service;
//    private final UsuarioAutenticacaoService usuarioAutenticacaoService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis
                .stream()
                .map(perfil -> new SimpleGrantedAuthority("ROLE_"+perfil.getPerfil().getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return usuario;
    }

    @Override
    public Object getPrincipal() {
        return usuario;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return usuario.getLogin();
    }
}
