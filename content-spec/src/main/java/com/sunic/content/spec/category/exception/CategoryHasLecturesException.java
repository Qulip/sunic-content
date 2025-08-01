package com.sunic.content.spec.category.exception;

/**
 * Domain exception for when trying to delete a category that has lectures.
 */
public class CategoryHasLecturesException extends RuntimeException {

	public CategoryHasLecturesException(Integer id) {
		super("Cannot delete category with id: " + id + " because it has associated lectures");
	}

	public CategoryHasLecturesException(String message) {
		super(message);
	}

	public CategoryHasLecturesException(String message, Throwable cause) {
		super(message, cause);
	}
}