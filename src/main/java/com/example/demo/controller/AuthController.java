package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.jwt.JwtService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UserService userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        try {
            // ⭐ CAMBIO 1: Extraer el campo 'username' que ahora es independiente
            String username = body.get("username");
            String useremail = body.get("useremail");
            String password = body.get("password");
            String role = body.getOrDefault("role", "USER");

            // ⭐ CAMBIO 2: Validar que el nuevo campo 'username' también esté presente
            if (username == null || useremail == null || password == null ||
                    username.isBlank() || useremail.isBlank() || password.isBlank()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Username, useremail, y password son requeridos para el registro"));
            }

            if (!role.equals("USER") && !role.equals("ADMIN")) {
                role = "USER";
            }

            // ⭐ CAMBIO 3: Llamar al método register de 4 parámetros, ahora con todos los campos separados
            userService.register(username, useremail, password, role);

            return ResponseEntity.ok(Map.of(
                    "message", "Usuario registrado correctamente",
                    "role", role
            ));

        } catch (Exception e) {
            // Se asume que el error es por unicidad de username o email (columna unique=true en User.java)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "El usuario ya existe (Username o Email ya registrados)"));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            // ⭐ CAMBIO 4: Usamos 'username' en el login, ya que es el identificador único en la BBDD
            String username = body.get("username");
            String password = body.get("password");

            if (username == null || password == null || username.isBlank() || password.isBlank()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Username y password son requeridos para iniciar sesión"));
            }

            // Usamos el username para la autenticación
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            if (auth.isAuthenticated()) {
                // Obtener rol del usuario
                User user = userService.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                String token = jwtService.generateToken(username, user.getRole());

                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "username", username,
                        "role", user.getRole()
                ));
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Nombre de usuario o contraseña incorrectos"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al procesar la solicitud"));
        }
    }
}