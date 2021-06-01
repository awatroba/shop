package com.awatroba.shop.models;

import com.awatroba.shop.enums.CategoryProduct;

import javax.persistence.*;

/**
 * @author Angelika
 * Object that stores products data.
 */
@Entity
@Table(name="Product")
//TODO: add the number of products in the future
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_category")
    private CategoryProduct category;

    public Product() {
    }

    public Product(String name, double price,  String description, CategoryProduct category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public CategoryProduct getCategory() {
        return category;
    }
}