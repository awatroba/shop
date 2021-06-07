package com.awatroba.shop.exception;

/**
 * @author Angelika
 * Category Not Found Exception
 */
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("Category not found exception ");
    }
}

