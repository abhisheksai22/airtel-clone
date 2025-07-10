package com.abhi.security.service;

import com.abhi.security.entity.AirtelUser;
import com.abhi.security.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepo  userRepo;

    private final AuthenticationManager  authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final JwtService jwtService;

    public UserService(UserRepo userRepo,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AirtelUser registerUser(AirtelUser airtelUser) {
        airtelUser.setPassword(encoder.encode(airtelUser.getPassword()));
        log.info("user password: {}", airtelUser.getPassword());
        if (userRepo.findByUsername(airtelUser.getUsername()).isPresent()) {
            //TODO :- global exception handler
            throw new RuntimeException("username already exists");
        }

        return userRepo.save(airtelUser);
    }

    public String loginUser(AirtelUser airtelUser) {
        log.info("inside loginUser:{}", airtelUser);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                airtelUser.getUsername(),
                                airtelUser.getPassword()));
        log.info("authentication: {}", authentication);
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(airtelUser);
        } else {
            return "fail";
        }
    }
}
