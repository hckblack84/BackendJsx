package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String useremail;

    @JsonIgnore // ⭐ IMPORTANTE: Evita que la contraseña se envíe en las respuestas JSON
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // "ADMIN" o "USER"
}