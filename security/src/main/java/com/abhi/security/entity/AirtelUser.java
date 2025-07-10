package com.abhi.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    private String email;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "airtel_user_id")
    )
    @Enumerated(EnumType.STRING)
    private Set<Role>  roles;

}
