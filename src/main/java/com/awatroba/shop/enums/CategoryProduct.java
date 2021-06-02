package com.awatroba.shop.enums;

/**
 *
 * @author Angelika
 * enum for category product
 */
public enum CategoryProduct {
    CLOTHES("Clothes"),
    OTHERS("Others"),
    ELECTORICS("Electrics"),
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
}