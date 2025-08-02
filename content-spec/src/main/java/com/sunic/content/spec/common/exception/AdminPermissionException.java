package com.sunic.content.spec.common.exception;

/**
 * Exception thrown when a user without admin permissions attempts to perform an operation that requires admin privileges.
 */
public class AdminPermissionException extends RuntimeException {
	public AdminPermissionException(Integer userId) {
		super("User with id " + userId + " does not have admin permissions to perform this operation");
	}

	public AdminPermissionException(String message) {
		super(message);
	}
}