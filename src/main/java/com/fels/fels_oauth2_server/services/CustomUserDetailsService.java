package com.fels.fels_oauth2_server.services;

import com.fels.fels_oauth2_server.entities.UserDetail;
import com.fels.fels_oauth2_server.models.SecurityUser;
import com.fels.fels_oauth2_server.repositories.UserDetailRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDetailRepository userDetailRepository;

    public CustomUserDetailsService(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetail> userDetail = userDetailRepository.findByUsername(username);
        return userDetail.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
