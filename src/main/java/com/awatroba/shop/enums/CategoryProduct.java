package com.awatroba.shop.enums;

import java.util.stream.Stream;

/**
 *
 * @author Angelika
 * enum for category product
 */
public enum CategoryProduct {
    CLOTHES("Clothes"),
    OTHERS("Others"),
    ELECTRICS("Electrics"),
    ACCESSORIES("Accessories"),
    BAGS("Bags"),
    SHOES("Shoes"),
    TOYS("Toys"),
    BEAUTY("Beauty"),
    HEALTH("Health"),
    HOME("Home & Appliances"),
    JEWELRY("Jewelery & Watches"),
    PET("Pet");
    private String displayName;

    CategoryProduct(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    public static Stream<CategoryProduct> stream() {
        return Stream.of(CategoryProduct.values());
    }
}