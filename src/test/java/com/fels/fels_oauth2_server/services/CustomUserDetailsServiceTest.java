package com.fels.fels_oauth2_server.services;

import com.fels.fels_oauth2_server.entities.Authority;
import com.fels.fels_oauth2_server.entities.PasswordAlgorithm;
import com.fels.fels_oauth2_server.entities.User;
import com.fels.fels_oauth2_server.entities.UserDetail;
import com.fels.fels_oauth2_server.repositories.AuthorityRepository;
import com.fels.fels_oauth2_server.repositories.PasswordAlgorithmRepository;
import com.fels.fels_oauth2_server.repositories.UserDetailRepository;
import com.fels.fels_oauth2_server.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    @Mock private PasswordAlgorithmRepository passwordAlgorithmRepository;
    @Mock private AuthorityRepository authorityRepository;
    @Mock private UserRepository userRepository;
    @Mock private UserDetailRepository userDetailRepository;
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        customUserDetailsService = new CustomUserDetailsService(
                userDetailRepository,userRepository,
                authorityRepository, passwordAlgorithmRepository
        );
    }

    @Test
    void savePasswordAlgorithm() {
        //given
        PasswordAlgorithm passwordAlgorithm = new PasswordAlgorithm(
                "bcrypt","bcrypt algorithm identifier"
        );
        //when
        customUserDetailsService.savePasswordAlgorithm(passwordAlgorithm);
        //then
        ArgumentCaptor<PasswordAlgorithm> passwordAlgorithmArgumentCaptor =
                ArgumentCaptor.forClass(PasswordAlgorithm.class);

        verify(passwordAlgorithmRepository).save(passwordAlgorithmArgumentCaptor.capture());

        PasswordAlgorithm capturedPasswordAlgorithm = passwordAlgorithmArgumentCaptor.getValue();

        assertThat(capturedPasswordAlgorithm).isEqualTo(passwordAlgorithm);
    }

    @Test
    void saveAuthority() {
        //given
        Authority authority = new Authority(
                "read","Read authority - Can only read content"
        );
        //when
        authorityRepository.save(authority);
        //then
        ArgumentCaptor<Authority> authorityArgumentCaptor =
                ArgumentCaptor.forClass(Authority.class);
        verify(authorityRepository).save(authorityArgumentCaptor.capture());
        Authority capturedAuthority = authorityArgumentCaptor.getValue();

        assertThat(capturedAuthority).isEqualTo(authority);
    }

    @Test
    void saveUserDetail() {
        //given
        PasswordAlgorithm passwordAlgorithm = new PasswordAlgorithm(
                "bcrypt","bcrypt algorithm identifier"
        );
        customUserDetailsService.savePasswordAlgorithm(passwordAlgorithm);
        Authority authority = new Authority(
                "read","Read authority - Can only read content"
        );
        authorityRepository.save(authority);
        User user = new User(
                "","","","",
                "","","felixnjunge78@gmail.com",
                passwordAlgorithm,"$12345"
        );
        userRepository.save(user);
        //when
        UserDetail userDetail = new UserDetail(user,authority);
        userDetailRepository.save(userDetail);
        //then
        ArgumentCaptor<UserDetail> userDetailArgumentCaptor =
                ArgumentCaptor.forClass(UserDetail.class);

        verify(userDetailRepository).save(userDetailArgumentCaptor.capture());

        UserDetail capturedUserDetail = userDetailArgumentCaptor.getValue();

        assertThat(capturedUserDetail).isEqualTo(userDetail);

    }

    @Test
    void saveUser() {
        //given
        PasswordAlgorithm passwordAlgorithm = new PasswordAlgorithm(
                "bcrypt","bcrypt algorithm identifier"
        );
        customUserDetailsService.savePasswordAlgorithm(passwordAlgorithm);
        User user = new User(
                "","","","",
                "","","felixnjunge78@gmail.com",
                passwordAlgorithm,"$12345"
        );
        //when
        userRepository.save(user);
        //then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }
}