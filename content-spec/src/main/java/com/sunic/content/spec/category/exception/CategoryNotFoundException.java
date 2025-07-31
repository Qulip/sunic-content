package com.sunic.content.spec.category.exception;

/**
 * Domain exception for category not found scenarios.
 * This follows the modularization-strategy.md pattern for domain exceptions.
 */
public class CategoryNotFoundException extends RuntimeException {
    
    public CategoryNotFoundException(Integer id) {
        super("Category not found with id: " + id);
    }
    
    public CategoryNotFoundException(String message) {
        super(message);
    }
    
    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}