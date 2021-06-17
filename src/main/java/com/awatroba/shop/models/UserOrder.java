package com.awatroba.shop.models;

import com.awatroba.shop.enums.DeliveryMethod;
import com.awatroba.shop.enums.PayMethod;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Angelika
 * Object that stores order data.
 */
@Entity
@Table(name = "UserOrder")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_total_cost")
    private double totalCost;

    @Column(name = "order_is_closed",
            columnDefinition = "boolean default false")
    private boolean isClosed;

    //One user can have many order, so here we have a one-to-many mapping.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //One order can have many products, so here we have a one-to-many mapping.
    @OneToMany(mappedBy = "userOrder", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Product> products;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_pay_method")
    private PayMethod payMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_delivery_method")
    private DeliveryMethod deliveryMethod;

    public UserOrder() {
    }

    public UserOrder(User user) {
        this.user = user;
    }

    public UserOrder(int totalCost) {
        this.totalCost = totalCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void deleteProduct(Product prodToDelete) {
        this.products.remove(prodToDelete);
    }

}
