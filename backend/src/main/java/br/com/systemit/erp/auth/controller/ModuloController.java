package br.com.systemit.erp.auth.controller;

import br.com.systemit.erp.auth.model.Modulo;
import br.com.systemit.erp.auth.service.ModuloService;
import br.com.systemit.erp.auth.service.PerfilService;
import br.com.systemit.erp.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") Integer id) {
        return service.buscarPorId(id).map(modulo -> {
            service.deletar(modulo);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
