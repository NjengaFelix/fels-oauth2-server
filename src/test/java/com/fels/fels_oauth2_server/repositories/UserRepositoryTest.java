package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        //given
        User user = new User(
                "","","","",
                "","","felixnjunge78@gmail.com","$12345"
        );
        userRepository.save(user);
        //when
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        User expectedUser = optionalUser.orElseThrow();
        //then
        assertThat(expectedUser.getUsername()).contains(user.getUsername());
    }
}