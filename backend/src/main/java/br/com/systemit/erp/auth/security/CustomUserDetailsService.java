package br.com.systemit.erp.auth.security;

import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.model.Usuario;
import br.com.systemit.erp.auth.model.UsuarioAutenticacao;
import br.com.systemit.erp.auth.service.UsuarioAutenticacaoService;
import br.com.systemit.erp.auth.service.UsuarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService{

    private final UsuarioService service;
    private final UsuarioAutenticacaoService usuarioAutenticacaoService;

    public CustomUserDetailsService(UsuarioService service, UsuarioAutenticacaoService usuarioAutenticacaoService) {
        this.service = service;
        this.usuarioAutenticacaoService = usuarioAutenticacaoService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("Localizando:... " + login);
        Usuario usuario = service.buscaByLogin(login);
        System.out.println("Localizado ;-) ");

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado.");
        }

        // busca os perfis de acesso
        List<UsuarioAutenticacao> perfis = usuarioAutenticacaoService.buscaByUsuario(usuario);

        String[] roles = perfis.stream()
                .map(p -> p.getPerfil().getRole()) // Mapeia para os roles
                //.peek(System.out::println)        // Imprime cada role
                .toArray(String[]::new);

        System.out.println("qtdPerfis:" + Arrays.toString(roles));

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
