package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByClientId(String clientId);
}
