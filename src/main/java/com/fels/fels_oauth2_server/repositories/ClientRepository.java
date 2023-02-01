package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
