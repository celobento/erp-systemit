package br.com.systemit.erp.auth.security;

import br.com.systemit.erp.auth.model.Usuario;
import br.com.systemit.erp.auth.model.UsuarioAutenticacao;
import br.com.systemit.erp.auth.service.UsuarioAutenticacaoService;
import br.com.systemit.erp.auth.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;
    private final UsuarioAutenticacaoService usuarioAutenticacaoService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String senhaDigitada = authentication.getCredentials().toString();

        Usuario usuarioEncontrado = usuarioService.buscaByLogin(login);

        if (usuarioEncontrado == null) {
            throw new UsernameNotFoundException("Usuario e/ou senha incorreta.");
        }

        String senhaCriptografada = usuarioEncontrado.getSenha();

        boolean senhasBatem = encoder.matches(senhaDigitada, senhaCriptografada);

        if (senhasBatem) {
            List<UsuarioAutenticacao> perfis = usuarioAutenticacaoService.buscaByUsuario(usuarioEncontrado);
            return new CustomAuthentication(usuarioEncontrado, perfis);
        }

        return null;
    }

    // types authentications supported
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
