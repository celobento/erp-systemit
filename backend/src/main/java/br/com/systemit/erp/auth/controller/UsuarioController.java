package br.com.systemit.erp.auth.controller;

import br.com.systemit.erp.auth.dto.UsuarioDTO;
import br.com.systemit.erp.auth.mapper.UsuarioMapper;
import br.com.systemit.erp.auth.model.Usuario;
import br.com.systemit.erp.auth.service.UsuarioService;
import br.com.systemit.erp.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    public UsuarioController(UsuarioService service, UsuarioMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDTO dto){
        var usuario = mapper.toEntity(dto);
        System.out.println(usuario.getLogin());
        System.out.println(usuario.getSenha());
        service.salvar(usuario);
    }

    @GetMapping("porLogin")
    public ResponseEntity<String> buscaPorLogin(@RequestBody UsuarioDTO dto) {
        var usuario = mapper.toEntity(dto);
        Usuario user = service.buscaByLogin(usuario.getLogin());
        System.out.println("email: " + user.getEmail());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(JsonUtil.objectToJson(mapper.toDto(user)));
    }
}
