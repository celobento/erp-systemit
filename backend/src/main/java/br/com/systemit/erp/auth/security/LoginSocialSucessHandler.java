package br.com.systemit.erp.auth.security;

import br.com.systemit.erp.auth.model.Usuario;
import br.com.systemit.erp.auth.model.UsuarioAutenticacao;
import br.com.systemit.erp.auth.service.UsuarioAutenticacaoService;
import br.com.systemit.erp.auth.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginSocialSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UsuarioService usuarioService;
    private final UsuarioAutenticacaoService usuarioAutenticacaoService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = auth.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        System.out.println(email);
        Usuario usuario = usuarioService.buscaByEmail(email);
        if (usuario != null) {
            List<UsuarioAutenticacao> perfis = usuarioAutenticacaoService.buscaByUsuario(usuario);
            CustomAuthentication customAuth = new CustomAuthentication(usuario, perfis);
            SecurityContextHolder.getContext().setAuthentication(customAuth);
            super.onAuthenticationSuccess(request, response, customAuth);
        }
    }
}
