package com.sunic.content.spec.lecture.exception;

/**
 * Exception thrown when lecture is not found.
 */
public class LectureNotFoundException extends RuntimeException {
	public LectureNotFoundException(Integer id) {
		super("Lecture not found with id: " + id);
	}

	public LectureNotFoundException(String message) {
		super(message);
	}
}