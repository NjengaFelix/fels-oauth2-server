package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.Uri;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UriRepository extends JpaRepository<Uri,Long> {

    Optional<Uri> findByName(String redirectUri);
}
