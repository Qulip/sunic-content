package com.sunic.content.spec.content.exception;

/**
 * Exception thrown when content URL is invalid.
 */
public class InvalidContentUrlException extends RuntimeException {
    public InvalidContentUrlException(String message) {
        super(message);
    }
}