package com.awatroba.shop.exception;
/**
 * @author Angelika
 * Product Not Found Exception
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product not found exception ");
    }
}
