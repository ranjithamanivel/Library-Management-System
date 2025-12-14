package com.example.Library.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

import java.util.Collection;

@Data
@Entity
@Table(name="library_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public enum Role {
        ADMIN,
        USER
    }
//    @ElementCollection(fetch = FetchType.EAGER)
//    private Set<String> roles = new HashSet<>(); // ROLE_USER, ROLE_ADMIN
}
