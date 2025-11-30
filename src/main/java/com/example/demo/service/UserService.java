package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ========== MÉTODOS DE REGISTRO ==========

    public User register(String username, String useremail, String password, String role) {
        User user = User.builder()
                .username(username)
                .useremail(useremail)
                .password(passwordEncoder.encode(password))
                .role(role != null ? role : "USER")
                .build();
        return userRepository.save(user);
    }

    public User register(String username, String useremail, String password) {
        return register(username, useremail, password, "USER");
    }

    // ========== MÉTODOS PARA PERFIL ==========

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User updatePassword(String username, String newPassword) {
        Optional<User> userOpt = findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        }
        return null;
    }

    // ========== MÉTODOS CRUD GENERALES ==========

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}