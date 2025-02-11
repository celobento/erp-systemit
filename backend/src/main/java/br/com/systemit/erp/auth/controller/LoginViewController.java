package br.com.systemit.erp.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {
    @GetMapping("/login")
    public String paginaLogin() {
        return "login";
    }

//    @Value("${spring.security.oauth2.client.registration.google.client-id}")
//    private String clientId;

    @GetMapping("/")
    @ResponseBody
    public String paginaHome(Authentication authentication) {
        return "Olá" + authentication.getName() ;
    }
}
