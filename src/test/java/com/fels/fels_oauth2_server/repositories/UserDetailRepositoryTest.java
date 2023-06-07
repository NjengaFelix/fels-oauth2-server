package com.fels.fels_oauth2_server.repositories;

import com.fels.fels_oauth2_server.entities.Authority;
import com.fels.fels_oauth2_server.entities.User;
import com.fels.fels_oauth2_server.entities.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserDetailRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Test
    void findByUsername() {
        //given
        User user = new User(
                "","","","",
                "","","felixnjunge78@gmail.com","$12345"
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