package com.example.demo.dataLoader;

import com.example.demo.dataLoader.LinkImages;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner, LinkImages {

    // Se mantiene como está en tu código original, aunque no se usa
    private final String[] linkImages = {};

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Producto original de tu código
        Product originalProduct = Product.builder()
                .name("Audifonos muy gamer")
                .categories("Pasteles")
                .price(1000)
                .image(pastel1)
                .stock(true)
                .discount(false)
                .stars(2)
                .build();


        List<Product> products = Arrays.asList(originalProduct);

        productRepository.saveAll(products);

        // --- Definición de Usuarios ---

        User user1 = User.builder()
                .username("user_user")
                .useremail("hola@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role("USER")
                .build();

        User user2 = User.builder()
                .username("user_admin")
                .useremail("lkasdk@gmail.com")
                .password(passwordEncoder.encode("admin123"))
                .role("ADMIN")
                .build();


        userRepository.save(user1);
        userRepository.save(user2);
        System.out.println("Data loaded.");
    }
}