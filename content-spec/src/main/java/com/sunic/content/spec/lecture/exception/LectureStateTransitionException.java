package com.sunic.content.spec.lecture.exception;

import com.sunic.content.spec.lecture.entity.LectureState;

/**
 * Exception thrown when invalid lecture state transition is attempted.
 */
public class LectureStateTransitionException extends RuntimeException {
    public LectureStateTransitionException(LectureState from, LectureState to) {
        super("Invalid lecture state transition from " + from + " to " + to);
    }
    
    public LectureStateTransitionException(String message) {
        super(message);
    }
}