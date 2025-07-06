package com.abhi.security.repo;

import com.abhi.security.entity.AirtelUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AirtelUser, Long> {
    Optional<AirtelUser> findByUsername(String username);
}
