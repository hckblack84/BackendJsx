package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Api("User Management System")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // ========== ENDPOINT PERFIL (NUEVO Y PRINCIPAL) ==========

    @GetMapping("/profile")
    @ApiOperation("Get authenticated user profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Optional<User> userOpt = userService.findByUsername(username);

            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            User user = userOpt.get();

            // Crear respuesta sin la contraseña (aunque @JsonIgnore ya la oculta)
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            response.put("email", user.getUseremail());
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Error al obtener perfil: " + e.getMessage()
            ));
        }
    }

    @PutMapping("/profile")
    @ApiOperation("Update authenticated user profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody Map<String, String> updates) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Optional<User> userOpt = userService.findByUsername(username);

            if (userOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            User user = userOpt.get();

            // Actualizar solo los campos permitidos
            if (updates.containsKey("useremail")) {
                user.setUseremail(updates.get("useremail"));
            }

            User updatedUser = userService.updateUser(user);

            Map<String, Object> response = new HashMap<>();
            response.put("id", updatedUser.getId());
            response.put("username", updatedUser.getUsername());
            response.put("email", updatedUser.getUseremail());
            response.put("role", updatedUser.getRole());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Error al actualizar perfil: " + e.getMessage()
            ));
        }
    }


    @GetMapping
    @ApiOperation(value = "View a list of available users", response = List.class)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get user by Id")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiOperation("Add new user")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete user by Id")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            return ResponseEntity.ok(Map.of("message", "Usuario eliminado correctamente"));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update user")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.getUserById(id);

        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setUseremail(user.getUseremail());
            existingUser.setRole(user.getRole());
            // NO actualizar password aquí directamente por seguridad
            return ResponseEntity.ok(userService.saveUser(existingUser));
        }
        return ResponseEntity.notFound().build();
    }

}