package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.GrantType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrantTypeRepository extends JpaRepository<GrantType,Long> {
    Optional<GrantType> findByName(String grantType);
}
