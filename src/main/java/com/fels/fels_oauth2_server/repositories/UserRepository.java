package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
