package br.com.systemit.erp.auth.service;

import br.com.systemit.erp.auth.model.Client;
import br.com.systemit.erp.auth.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client salvar(Client client) {
        String passEncrypted = encoder.encode(client.getClientSecret());
        client.setClientSecret(passEncrypted);
        return repository.save(client);
    }

    public Client obterPorClientId(String clientId) {
        return repository.findByClientId(clientId);
    }

}
