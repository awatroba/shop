package com.awatroba.shop.models;

import javax.persistence.*;

/**
 * @author Angelika
 * Object that stores order data.
 */
@Entity
@Table(name="UserOrder")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_total_cost")
    private int totalCost;

    @Column(name = "order_is_closed",
            columnDefinition = "boolean default false")
    private boolean isClosed;

   /* @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_products")
    private Set<Product> products;

    @Column(name = "order_pay_method")
    private PayMethod payMethod;*/

    public UserOrder() {
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

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
