package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScopeRepository extends JpaRepository<Scope,Long> {
    Optional<Scope> findByName(String scope);
}
