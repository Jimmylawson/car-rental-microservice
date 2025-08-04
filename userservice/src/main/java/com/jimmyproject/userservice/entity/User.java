package com.jimmyproject.userservice.entity;


import com.jimmyproject.userservice.enums.Roles;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
    private boolean enabled;
}
