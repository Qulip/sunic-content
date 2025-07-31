package com.sunic.content.spec.lecture.exception;

/**
 * Exception thrown when lecture already exists.
 */
public class LectureAlreadyExistsException extends RuntimeException {
    public LectureAlreadyExistsException(String message) {
        super(message);
    }
}