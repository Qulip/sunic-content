package com.sunic.content.spec.content.exception;

import com.sunic.content.spec.content.entity.ContentState;

/**
 * Exception thrown when invalid content state transition is attempted.
 */
public class ContentStateTransitionException extends RuntimeException {
	public ContentStateTransitionException(ContentState from, ContentState to) {
		super("Invalid content state transition from " + from + " to " + to);
	}

	public ContentStateTransitionException(String message) {
		super(message);
	}
}