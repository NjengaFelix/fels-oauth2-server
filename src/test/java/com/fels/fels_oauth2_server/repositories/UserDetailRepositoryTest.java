package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.Authority;
import com.fels.fels_oauth2_server.entities.PasswordAlgorithm;
import com.fels.fels_oauth2_server.entities.User;
import com.fels.fels_oauth2_server.entities.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailRepositoryTest {
    @Autowired
    private PasswordAlgorithmRepository passwordAlgorithmRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

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
        Authority authority = new Authority(
                "read","Read authority - Can only read content"
        );
        authorityRepository.save(authority);


        UserDetail actualUserDetail = userDetailRepository.save(new UserDetail(user, authority));

        //when
        Optional<UserDetail> optionalUserDetail = userDetailRepository.findByUsername("felixnjunge78@gmail.com");

        //then
        UserDetail expectUserDetail = optionalUserDetail.get();
        assertThat(actualUserDetail.getUser().getUsername()).contains(expectUserDetail.getUser().getUsername());
    }


}