package com.awatroba.shop.models;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Angelika
 * Object that stores shopping cart.
 */
@Entity
@Table(name = "Cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long id;

    //One cart can have many items, so here we have a one-to-many mapping.
    @OneToMany(mappedBy = "cart")
    private Set<Product> products;

    //One cart can have one user, so here we have a one-to-one mapping.
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product products) {
        this.products.add(products);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
