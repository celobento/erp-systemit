package br.com.systemit.erp.auth.controller;

import br.com.systemit.erp.auth.dto.ErroResposta;
import br.com.systemit.erp.auth.dto.PerfilDTO;
import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.model.Usuario;
import br.com.systemit.erp.auth.repository.PerfilRepository;
import br.com.systemit.erp.auth.security.SecurityService;
import br.com.systemit.erp.auth.service.ModuloService;
import br.com.systemit.erp.auth.service.PerfilService;
import br.com.systemit.erp.auth.service.UsuarioService;
import br.com.systemit.erp.exceptions.RegistroDuplicadoException;
import br.com.systemit.erp.util.JsonUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("perfis")
public class PerfilController {

    private PerfilService service;
    private ModuloService serviceModulo;
    //private UsuarioService usuarioService;
    private SecurityService securityService;

    public PerfilController(PerfilService service,
                            SecurityService securityService,
                            ModuloService serviceModulo
            //, UsuarioService usuarioService
        ) {
        this.service = service;
        this.serviceModulo = serviceModulo;
        //this.usuarioService = usuarioService;
        this.securityService = securityService;
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid PerfilDTO perfilDTO) {
        try {

       Perfil novoPerfil = perfilDTO.mapearParaPerfil();
        novoPerfil = service.salvar(novoPerfil);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoPerfil.getId())
                .toUri();

        return ResponseEntity
                .status(HttpStatus.OK)
                //.body(novoPerfil)
                .body(location);
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.respostaPadrão(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PerfilDTO> buscaPorId(@PathVariable("id") Integer id) {
        Optional<Perfil> perfilOptional = service.buscarPorId(id);
        if(perfilOptional.isPresent()) {
            Perfil perfil = perfilOptional.get();
            PerfilDTO perfilDTO = new PerfilDTO(
                    perfil.getNome(),
                    perfil.getDiscriminacao(),
                    perfil.getRole(),
                    perfil.getModulo().getId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(perfilDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
        Optional<Perfil> perfilOptional = service.buscarPorId(id);
        if(perfilOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(perfilOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("modulo/{id}")
    public ResponseEntity<String> buscaPorModulo(@PathVariable("id") Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(JsonUtil.objectToJson(service.buscarPorModulo(id)));
    }

//    @GetMapping()
//    @PreAuthorize("hasAnyRole('adm')")
//    public ResponseEntity<String> findAll(
//            //Authentication authentication
//            ) {
//
//        //UserDetails usuarioLogado = (UserDetails) authentication.getPrincipal();
//        //Usuario usuario = usuarioService.buscaByLogin(usuarioLogado.getUsername());
//        Usuario usuario = securityService.obterUsuarioLogado();
//        System.out.println("User logged: " + usuario.getLogin());
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(JsonUtil.objectToJson(service.findAll()));
//    }

    @GetMapping
    public ResponseEntity<List<PerfilDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "role", required = false) String role
    ){
        List<Perfil> resultado = service.pesquisar(nome, role);
        List<PerfilDTO> lista = resultado
                .stream()
                .map(perfil -> new PerfilDTO(
                        perfil.getNome(),
                        perfil.getDiscriminacao(),
                        perfil.getRole(),
                        perfil.getModulo().getId())
                ).collect(Collectors.toList());
        return ResponseEntity.ok().body(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable(name = "id") Integer id,
            @RequestBody PerfilDTO perfilDTO) {
        Optional<Perfil> perfilOptional = service.buscarPorId(id);
        if(perfilOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Perfil perfil = perfilOptional.get();
        perfil.setNome(perfilDTO.nome());
        perfil.setRole(perfilDTO.role());
        perfil.setDiscriminacao(perfilDTO.discriminacao());
        if (perfilDTO.modulo() != null &&
                perfilDTO.modulo().intValue() != perfil.getModulo().getId().intValue()) {
            Optional<Modulo> moduloOptional = serviceModulo.buscarPorId(perfilDTO.modulo());
            perfil.setModulo(moduloOptional.isPresent() ? moduloOptional.get() : perfil.getModulo());
        }

        service.atualizar(perfil);
        return ResponseEntity.noContent().build();

    }
}
