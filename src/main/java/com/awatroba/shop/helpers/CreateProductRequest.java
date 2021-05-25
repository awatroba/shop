package com.awatroba.shop.helpers;

import com.awatroba.shop.enums.CategoryProduct;
/**
 * @author Angelika
 * Model of create produt request
 */
public class CreateProductRequest {
    private String name;
    private float price;
    private String description;
    private CategoryProduct category;

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public CategoryProduct getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
