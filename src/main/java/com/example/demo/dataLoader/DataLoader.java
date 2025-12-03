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
                .name("pastel de chocolate")
                .categories("Pasteles")
                .price(1000)
                .image(pastel1)
                .stock(true)
                .discount(false)
                .stars(2)
                .build();


        Product  torta1  = Product.builder()
                .name("tora de frutillas")
                .categories("Pasteles")
                .price(1000)
                .image(pastel2)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();


        Product  torta2  = Product.builder()
                .name("Torta Tres Leches")
                .categories("Pasteles")
                .price(1000)
                .image(pastel3)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();


        Product  torta3  = Product.builder()
                .name("Pastel Selva Negra")
                .categories("Pasteles")
                .price(1000)
                .image(pastel4)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();

        Product  torta4  = Product.builder()
                .name("Pastel de Zanahoria")
                .categories("Pasteles")
                .price(1000)
                .image(pastel5)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();

        Product  torta5  = Product.builder()
                .name("Tiramisú")
                .categories("Pasteles")
                .price(1000)
                .image(pastel6)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();

        Product  muffins1  = Product.builder()
                .name("hispas de Chocolate")
                .categories("muffins")
                .price(1000)
                .image(muffin1)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();

        Product  muffins2  = Product.builder()
                .name("Muffins de Arándanos")
                .categories("muffins")
                .price(1000)
                .image(muffin2)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();

        Product  muffins3  = Product.builder()
                .name("Muffins de Plátano y Nueces")
                .categories("muffins")
                .price(1000)
                .image(muffin3)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();


        Product  Galletas1  = Product.builder()
                .name("Galletas con Chispas de Chocolate")
                .categories("Galletas")
                .price(1000)
                .image(Galleta1)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();


        Product  Galletas2  = Product.builder()
                .name("Galletas de Avena")
                .categories("Galletas")
                .price(1000)
                .image(Galleta2)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();



        Product  Galletas3  = Product.builder()
                .name("Galletas de Mantequilla")
                .categories("Galletas")
                .price(1000)
                .image(Galleta3)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();



        Product  Donuts2 = Product.builder()
                .name("Donut Relleno de Mermelada")
                .categories("Donuts")
                .price(1000)
                .image(Donut2)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();


        Product  Donuts3 = Product.builder()
                .name("Donut Relleno de Crema Bávara")
                .categories("Donuts")
                .price(1000)
                .image(Donut3)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();

        Product  Donuts4 = Product.builder()
                .name("Donut Glaseado Clásico")
                .categories("Donuts")
                .price(1000)
                .image(Donut4)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();

        Product  POSTR1 = Product.builder()
                .name("tora de frutillas")
                .categories("Postres")
                .price(1000)
                .image(Donut4)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();


        Product  POSTR2 = Product.builder()
                .name("tora de frutillas")
                .categories("Postres")
                .price(1000)
                .image(Donut4)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();


        Product  POSTR3 = Product.builder()
                .name("tora de frutillas")
                .categories("Postres")
                .price(1000)
                .image(Donut4)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();

        Product  POSTR4 = Product.builder()
                .name("tora de frutillas")
                .categories("Postres")
                .price(1000)
                .image(Donut4)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();

        Product  POSTR5 = Product.builder()
                .name("Mousse de Chocolate")
                .categories("Postres")
                .price(1000)
                .image(Donut4)
                .stock(true)
                .discount(false)
                .stars(5)
                .build();


        List<Product> products = Arrays.asList(originalProduct,torta1,torta2,torta3,torta4,torta5,muffins1,muffins2,muffins3,Galletas1,Galletas2,Galletas3,Donuts2,Donuts3,Donuts4, POSTR1,POSTR2,POSTR3,POSTR4,POSTR5);


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