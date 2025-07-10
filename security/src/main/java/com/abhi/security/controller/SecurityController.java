package com.abhi.security.controller;

import com.abhi.security.entity.AirtelUser;
import com.abhi.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security")
@Slf4j
public class SecurityController {

    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "Hello World" + request;
    }

    @PostMapping("/register")
    public AirtelUser register(@RequestBody AirtelUser airtelUser) {
        AirtelUser user = userService.registerUser(airtelUser);
        log.info("user: {}", user);
        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody AirtelUser airtelUser) {
        log.info("login airtelUser:{}", airtelUser);
        return userService.loginUser(airtelUser);
    }

}
