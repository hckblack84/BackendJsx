package com.example.demo.dataLoader;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner, LinkImages {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Limpiar repositorios antes de cargar si es necesario (opcional)
        // productRepository.deleteAll();
        // userRepository.deleteAll();

        // 1. --- Definición de Productos de Repostería ---

        List<Product> products = new ArrayList<>();

        // Pasteles (Cakes)
        products.add(Product.builder()
                .name("Torta de Chocolate Clásica")
                .categories("Pasteles")
                .price(28000)
                .image(pastel1)
                .stock(true)
                .discount(false)
                .stars(5)
                .build());

        products.add(Product.builder()
                .name("Pastel Red Velvet de Capas")
                .categories("Pasteles")
                .price(35000)
                .image(pastel2)
                .stock(true)
                .discount(false)
                .stars(4)
                .build());

        products.add(Product.builder()
                .name("Tarta de Frutillas Frescas")
                .categories("Pasteles")
                .price(22500)
                .image(pastel3)
                .stock(true)
                .discount(false)
                .stars(5)
                .build());

        products.add(Product.builder()
                .name("Pastel de Vainilla con Merengue")
                .categories("Pasteles")
                .price(25000)
                .image(pastel4)
                .stock(true)
                .discount(false)
                .stars(3)
                .build());

        products.add(Product.builder()
                .name("Torta de Celebración Festiva")
                .categories("Pasteles")
                .price(40000)
                .image(pastel5)
                .stock(true)
                .discount(false)
                .stars(4)
                .build());

        products.add(Product.builder()
                .name("Pastel Selva Negra Premium")
                .categories("Pasteles")
                .price(32000)
                .image(pastel6)
                .stock(true)
                .discount(true) // Descuento aplicado
                .stars(5)
                .build());


        // Muffins y Cupcakes
        products.add(Product.builder()
                .name("Muffin de Doble Chocolate")
                .categories("muffins")
                .price(3500)
                .image(muffin1)
                .stock(true)
                .discount(false)
                .stars(4)
                .build());

        products.add(Product.builder()
                .name("Cupcake de Vainilla y Crema")
                .categories("muffins")
                .price(4000)
                .image(muffin2)
                .stock(true)
                .discount(false)
                .stars(5)
                .build());

        products.add(Product.builder()
                .name("Muffin de Arándanos Clásico")
                .categories("muffins")
                .price(3200)
                .image(muffin3)
                .stock(true)
                .discount(false)
                .stars(3)
                .build());


        // Galletas (Cookies)
        products.add(Product.builder()
                .name("Galletas Decoradas de Azúcar")
                .categories("Galletas")
                .price(1800)
                .image(Galleta1)
                .stock(true)
                .discount(false)
                .stars(4)
                .build());

        products.add(Product.builder()
                .name("Galleta de Chispas de Chocolate (Pack)")
                .categories("Galletas")
                .price(5000)
                .image(Galleta2)
                .stock(true)
                .discount(false)
                .stars(5)
                .build());

        products.add(Product.builder()
                .name("Galletas de Mantequilla Simples")
                .categories("Galletas")
                .price(1500)
                .image(Galleta3)
                .stock(true)
                .discount(true) // Descuento aplicado
                .stars(3)
                .build());


        // Donas (Donuts)
        products.add(Product.builder()
                .name("Donas Glaseadas de Colores")
                .categories("Donuts")
                .price(2500)
                .image(Donut2)
                .stock(true)
                .discount(false)
                .stars(4)
                .build());

        products.add(Product.builder()
                .name("Donas con Cobertura de Chocolate")
                .categories("Donuts")
                .price(2800)
                .image(Donut3)
                .stock(true)
                .discount(false)
                .stars(5)
                .build());

        products.add(Product.builder()
                .name("Donas Clásicas de Glaseado Blanco")
                .categories("Donuts")
                .price(2200)
                .image(Donut4)
                .stock(true)
                .discount(false)
                .stars(4)
                .build());

        // Pan Misceláneo
        products.add(Product.builder()
                .name("Pan Rústico de Masa Madre")
                .categories("Postres")
                .price(5500)
                .image(Pan_duro)
                .stock(true)
                .discount(false)
                .stars(4)
                .build());

        // Guardar todos los productos en el repositorio
        productRepository.saveAll(products);

        // 2. --- Definición de Usuarios ---

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

        System.out.println("Data loaded: " + products.size() + " products and 2 users.");
    }
}