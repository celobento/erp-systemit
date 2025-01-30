package br.com.systemit.erp.auth.controller;

import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.service.ModuloService;
import br.com.systemit.erp.auth.service.PerfilService;
import br.com.systemit.erp.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("modulos")
public class ModuloController {

    private ModuloService service;

    public ModuloController(ModuloService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<String> buscaPorId(@PathVariable("id") Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(JsonUtil.objectToJson(service.buscarPorId(id)));
    }

    @GetMapping()
    public ResponseEntity<String> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(JsonUtil.objectToJson(service.findAll()));
    }
}
