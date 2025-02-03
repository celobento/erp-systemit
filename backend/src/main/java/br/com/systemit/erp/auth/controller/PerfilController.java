package br.com.systemit.erp.auth.controller;

import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.model.Usuario;
import br.com.systemit.erp.auth.repository.PerfilRepository;
import br.com.systemit.erp.auth.security.SecurityService;
import br.com.systemit.erp.auth.service.PerfilService;
import br.com.systemit.erp.auth.service.UsuarioService;
import br.com.systemit.erp.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("perfis")
public class PerfilController {

    private PerfilService  service;
    //private UsuarioService usuarioService;
    private SecurityService securityService;

    public PerfilController(PerfilService service,
                            SecurityService securityService
            //, UsuarioService usuarioService
        ) {
        this.service = service;
        //this.usuarioService = usuarioService;
        this.securityService = securityService;
    }

    @GetMapping("{id}")
    public ResponseEntity<String> buscaPorId(@PathVariable("id") Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(JsonUtil.objectToJson(service.buscarPorId(id)));
    }

    @GetMapping("modulo/{id}")
    public ResponseEntity<String> buscaPorModulo(@PathVariable("id") Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(JsonUtil.objectToJson(service.buscarPorModulo(id)));
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('adm')")
    public ResponseEntity<String> findAll(
            //Authentication authentication
            ) {

        //UserDetails usuarioLogado = (UserDetails) authentication.getPrincipal();
        //Usuario usuario = usuarioService.buscaByLogin(usuarioLogado.getUsername());
        Usuario usuario = securityService.obterUsuarioLogado();
        System.out.println("User logged: " + usuario.getLogin());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(JsonUtil.objectToJson(service.findAll()));
    }
}
