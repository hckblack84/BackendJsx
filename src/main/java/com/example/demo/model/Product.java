package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor; // 💡 Añadido: Constructor con todos los argumentos
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; // 💡 Añadido: Constructor sin argumentos (OBLIGATORIO para JPA)

// @Data: Genera Getters, Setters, equals, hashCode y toString
// @Builder: Permite usar Product.builder()...build()
// @NoArgsConstructor: Constructor por defecto para Hibernate
// @AllArgsConstructor: Constructor con todos los campos (requerido al usar @Builder con @NoArgsConstructor)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    // --- Campos de la Entidad ---

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;

    private String name;
    private String categories;
    private int price;
    private String image;

    // 💡 Corregido: De 'Stock' a 'stock' (convención camelCase en Java)
    private Boolean stock;

    // 💡 Corregido: De 'Discount' a 'discount' (convención camelCase en Java)
    private Boolean discount;

    private int stars;

    // ----------------------------------------------------------------------
    // NOTA: Los Getters y Setters explícitos que tenías (getId, setId, etc.)
    // han sido eliminados. La anotación @Data de Lombok los genera
    // automáticamente, haciendo que el código sea mucho más limpio y corto.
    // ----------------------------------------------------------------------
}