package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.ClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientDetailRepository extends JpaRepository<ClientDetail,Long> {
    @Query(
            """
                SELECT cd FROM ClientDetail cd JOIN cd.client c
                JOIN cd.authMethod JOIN cd.scope JOIN cd.uri
                JOIN cd.grantType
                WHERE c.id = :id
            """)
    public Optional<ClientDetail> findById(Integer id);

    @Query(
            """
                SELECT cd FROM ClientDetail cd JOIN cd.client c
                JOIN cd.authMethod JOIN cd.scope JOIN cd.uri
                JOIN cd.grantType
                WHERE c.clientId = :clientId
            """
    )
    public Optional<ClientDetail> findByClientId(String clientId);
}
