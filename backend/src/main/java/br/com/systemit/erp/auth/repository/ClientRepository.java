package br.com.systemit.erp.auth.repository;

import br.com.systemit.erp.auth.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByClientId(String clientId);
}
