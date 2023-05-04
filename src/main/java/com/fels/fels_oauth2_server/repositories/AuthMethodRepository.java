package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.AuthMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthMethodRepository extends JpaRepository<AuthMethod,Long> {

    Optional<AuthMethod> findByName(String authMethod);
}
