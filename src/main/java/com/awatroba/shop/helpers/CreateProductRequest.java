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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(CategoryProduct category) {
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = CategoryProduct.valueOf(category);
    }

    public CreateProductRequest(String name, String description, float price, CategoryProduct category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }
    public CreateProductRequest(){}
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
