package com.sunic.content.spec.student.exception;

/**
 * Exception thrown when lecture student is not found.
 */
public class LectureStudentNotFoundException extends RuntimeException {
	public LectureStudentNotFoundException(Integer id) {
		super("Lecture student not found with id: " + id);
	}

	public LectureStudentNotFoundException(String message) {
		super(message);
	}
}