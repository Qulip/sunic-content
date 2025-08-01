package com.sunic.content.spec.lecture.exception;

/**
 * Exception thrown when lecture category is invalid.
 */
public class InvalidLectureCategoryException extends RuntimeException {
	public InvalidLectureCategoryException(Integer categoryId) {
		super("Category not found with id: " + categoryId);
	}

	public InvalidLectureCategoryException(String message) {
		super(message);
	}
}