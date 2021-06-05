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
  /*  @OneToMany(mappedBy = "Cart")
    private Set<Product> products;
*/

    //One user can have one cart, so here we have a one-to-one mapping.
    @OneToOne(mappedBy = "shoppingCart", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private User user;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.user = user;
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
