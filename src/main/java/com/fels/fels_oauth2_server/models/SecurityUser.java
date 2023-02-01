package com.fels.fels_oauth2_server.models;

import com.fels.fels_oauth2_server.entities.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUser implements UserDetails {
    private final UserDetail userDetail;

    public SecurityUser(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userDetail.getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return userDetail.getUser().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
