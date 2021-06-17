package com.awatroba.shop.models;

import com.awatroba.shop.enums.CategoryProduct;

import javax.persistence.*;

/**
 * @author Angelika
 * Object that stores products data.
 */
@Entity
@Table(name="Product")
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

    @Column(name = "product_enable")
    private boolean enable;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_category")
    private CategoryProduct category;

    //One cart can have many items, so here we have a one-to-many mapping.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private ShoppingCart cart;

    //One order can have many product, so here we have a one-to-many mapping.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private UserOrder userOrder;

    public Product() {
    }

    public Product(String name, double price,  String description, CategoryProduct category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.enable=true;
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

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(CategoryProduct category) {
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
}
