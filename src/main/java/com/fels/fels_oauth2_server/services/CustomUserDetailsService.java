package com.fels.fels_oauth2_server.services;

import com.fels.fels_oauth2_server.entities.Authority;
import com.fels.fels_oauth2_server.entities.User;
import com.fels.fels_oauth2_server.entities.UserDetail;
import com.fels.fels_oauth2_server.models.SecurityUser;
import com.fels.fels_oauth2_server.repositories.AuthorityRepository;
import com.fels.fels_oauth2_server.repositories.UserDetailRepository;
import com.fels.fels_oauth2_server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDetailRepository userDetailRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public CustomUserDetailsService(UserDetailRepository userDetailRepository, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userDetailRepository = userDetailRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    //Save authority
    public Authority saveAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    //save user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //Save userDetail / assignAuthority
    public UserDetail saveUserDetail(User user, Authority authority) {
        return userDetailRepository.save(new UserDetail(user, authority));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        try {
            User user = optionalUser.get();
            logger.debug(user.getUserDetails().toString());
            return new SecurityUser(user);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User name not found");
        }
    }

    public List<GrantedAuthority> getUserAuthorities(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user != null) {
            authorities = user.getUserDetails()
                    .stream()
                    .map(a -> new SimpleGrantedAuthority(a.getAuthority().getName()))
                    .collect(Collectors.toList());
        }
        return authorities;
    }
}
