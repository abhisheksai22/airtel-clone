package com.abhi.security.service;

import com.abhi.security.entity.AirtelUser;
import com.abhi.security.entity.UserPrincipal;
import com.abhi.security.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyAirtelUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public MyAirtelUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AirtelUser> airtelUser = userRepo.findByUsername(username);
        if (airtelUser.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new UserPrincipal(airtelUser.get());
    }
}
