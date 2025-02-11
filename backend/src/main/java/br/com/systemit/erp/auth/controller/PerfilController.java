package br.com.systemit.erp.auth.controller;

import br.com.systemit.erp.auth.dto.ErroResposta;
import br.com.systemit.erp.auth.dto.PerfilDTO;
import br.com.systemit.erp.auth.dto.PesquisaPerfilDTO;
import br.com.systemit.erp.auth.mapper.PerfilMapper;
import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.model.Perfil;
import br.com.systemit.erp.auth.security.SecurityService;
import br.com.systemit.erp.auth.service.ModuloService;
import br.com.systemit.erp.auth.service.PerfilService;
import br.com.systemit.erp.exceptions.RegistroDuplicadoException;
import br.com.systemit.erp.util.JsonUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("perfis")
@RequiredArgsConstructor
@Tag(name = "Perfil")
public class PerfilController implements GenericController {

    private final PerfilService service;
    private final ModuloService serviceModulo;
    //private UsuarioService usuarioService;
    private final SecurityService securityService;
    private final PerfilMapper perfilMapper;

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @Operation(summary = "salvar", description = "Cadastrar novo perfil")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "999", description = "Erro não catalogado"),
    })
    public ResponseEntity<Object> salvar(@RequestBody @Valid PerfilDTO perfilDTO) {
//        try {

        //Perfil novoPerfil = perfilDTO.mapearParaPerfil();
        Perfil novoPerfil = perfilMapper.toEntity(perfilDTO);
        novoPerfil = service.salvar(novoPerfil);

        URI location = gerarHeaderLocaation(novoPerfil.getId());

        return ResponseEntity
                .status(HttpStatus.OK)
                //.body(novoPerfil)
                .body(location);
        /*} catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.respostaPadrão(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }*/
    }

    @GetMapping("{id}")
    public ResponseEntity<PesquisaPerfilDTO> buscaPorId(@PathVariable("id") Integer id) {
//        Optional<Perfil> perfilOptional = service.buscarPorId(id);
//        if(perfilOptional.isPresent()) {
//            Perfil perfil = perfilOptional.get();

//            PerfilDTO perfilDTO = new PerfilDTO(
//                    perfil.getNome(),
//                    perfil.getDiscriminacao(),
//                    perfil.getRole(),
//                    perfil.getModulo().getId());

//            PerfilDTO perfilDTO = perfilMapper.toDto(perfil);
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(perfilDTO);
//        return ResponseEntity.notFound().build();

            return service.buscarPorId(id)
                    .map(perfil -> {
                        PesquisaPerfilDTO dto = perfilMapper.toDto(perfil);
                        return ResponseEntity.ok(dto);
                    }).orElseGet(()-> ResponseEntity.notFound().build());
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
    public ResponseEntity<Page<PesquisaPerfilDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "role", required = false) String role,
            @RequestParam(value = "pagina", defaultValue = "1") Integer pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "1") Integer tamanhoPagina
    ){
        Page<Perfil> resultadoPesquisa = service.pesquisar(nome, role, pagina, tamanhoPagina);
//        List<PesquisaPerfilDTO> lista = resultado
//                .stream()
//                .map(perfilMapper::toDto
//                        /*perfil -> new PerfilDTO(
//                        perfil.getNome(),
//                        perfil.getDiscriminacao(),
//                        perfil.getRole(),
//                        perfil.getModulo())*/
//                ).collect(Collectors.toList());
//        return ResponseEntity.ok().body(lista);
        Page<PesquisaPerfilDTO> resultado = resultadoPesquisa.map(perfilMapper::toDto);
        return ResponseEntity.ok(resultado);
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
