package br.com.systemit.erp.auth.controller;

import br.com.systemit.erp.auth.model.Client;
import br.com.systemit.erp.auth.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('adm')")
    public void salvar(@RequestBody Client client) {
        clientService.salvar(client);
    }
}
