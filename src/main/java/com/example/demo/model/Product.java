//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    private String name;
    private String categories;
    private int price;
    private String image;
    private Boolean Stock;
    private Boolean Discount;
    private int stars;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return this.categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getStock() {
        return this.Stock;
    }

    public void setStock(Boolean stock) {
        this.Stock = stock;
    }

    public Boolean getDiscount() {
        return this.Discount;
    }

    public void setDiscount(Boolean discount) {
        this.Discount = discount;
    }

    public int getStars() {
        return this.stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
