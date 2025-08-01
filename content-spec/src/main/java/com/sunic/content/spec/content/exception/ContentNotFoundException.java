package com.sunic.content.spec.content.exception;

/**
 * Exception thrown when content is not found.
 */
public class ContentNotFoundException extends RuntimeException {
	public ContentNotFoundException(Integer id) {
		super("Content not found with id: " + id);
	}

	public ContentNotFoundException(String message) {
		super(message);
	}
}