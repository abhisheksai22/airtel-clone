package com.abhi.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {

    private final AirtelUser airtelUser;

    public UserPrincipal(AirtelUser airtelUser) {
        this.airtelUser = airtelUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return airtelUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return airtelUser.getPassword();
    }

    @Override
    public String getUsername() {
        return airtelUser.getUsername();
    }
}
