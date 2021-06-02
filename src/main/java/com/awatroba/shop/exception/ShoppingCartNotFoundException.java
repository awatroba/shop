package com.awatroba.shop.exception;

/**
 * @author Angelika
 * Shopping cart Not Found Exception
 */
public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException() {
        super("Shopping cart not found exception ");
    }
}

