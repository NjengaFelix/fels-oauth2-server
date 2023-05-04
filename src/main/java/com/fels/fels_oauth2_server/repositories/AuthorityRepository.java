package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
