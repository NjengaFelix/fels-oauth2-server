package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.PasswordAlgorithm;
import com.fels.fels_oauth2_server.entities.User;
import com.fels.fels_oauth2_server.entities.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordAlgorithmRepository passwordAlgorithmRepository;

    @Test
    void findByUsername() {
        //given
        PasswordAlgorithm passwordAlgorithm = new PasswordAlgorithm(
                "bcrypt","bcrypt algorithm identifier"
        );
        passwordAlgorithmRepository.save(passwordAlgorithm);

        User user = new User(
                "","","","",
                "","","felixnjunge78@gmail.com",
                passwordAlgorithm,"$12345"
        );
        userRepository.save(user);
        //when
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        User expectedUser = optionalUser.orElseThrow();
        //then
        assertThat(expectedUser.getUsername()).contains(user.getUsername());
    }
}