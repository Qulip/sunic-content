package com.sunic.content.spec.student.exception;

/**
 * Exception thrown when content student is not found.
 */
public class ContentStudentNotFoundException extends RuntimeException {
	public ContentStudentNotFoundException(Integer id) {
		super("Content student not found with id: " + id);
	}

	public ContentStudentNotFoundException(String message) {
		super(message);
	}
}