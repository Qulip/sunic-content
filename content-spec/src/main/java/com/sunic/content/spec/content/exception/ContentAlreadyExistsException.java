package com.sunic.content.spec.content.exception;

/**
 * Exception thrown when content already exists.
 */
public class ContentAlreadyExistsException extends RuntimeException {
	public ContentAlreadyExistsException(String message) {
		super(message);
	}
}