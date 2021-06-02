package com.awatroba.shop.models;

import com.awatroba.shop.enums.Role;

import javax.persistence.*;

/**
 * @author Angelika
 * Object that stores application user data.
 */
@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_login")
    private String login;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_pass")
    private String pass;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;

    //One user can have one cart, so here we have a one-to-one mapping.
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    public User(String login, String email, String pass, Role role) {
        this.login = login;
        this.email = email;
        this.pass = pass;
        this.role = role;
    }

    public User(String login,String email, String pass) {
        this.login = login;
        this.email = email;
        this.pass = pass;
        this.role=Role.USER;
    }

    public User() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
