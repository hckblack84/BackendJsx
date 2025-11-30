package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
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


    public User register(String username, String password, String role) {
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role != null ? role : "USER") // Por defecto USER
                .build();
        return userRepository.save(user);
    }

    @Autowired
    private UserRepository productRepository;

    public List<User> getAllUsers() {
        return this.productRepository.findAll();
    }

    public User getUserById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public User saveUser(User product) {
        return (User)this.productRepository.save(product);
    }

    public void deleteUser(Long id) {
        this.productRepository.deleteById(id);
    }

    public User register(String username, String password) {
        return register(username, password, "USER");
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
