package com.abhi.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirtelUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airtelUserId;

    private String username;
    private String password;

}
