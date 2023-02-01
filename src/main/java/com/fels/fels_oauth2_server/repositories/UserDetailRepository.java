package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {
    @Query("""
                SELECT ud FROM UserDetail ud JOIN ud.user u 
                JOIN ud.authority WHERE u.username = :username
""")
    public Optional<UserDetail> findByUsername(String username);
}
