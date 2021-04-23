package com.bsgrd.treasurer.edifact.exception;

public class ValidationException extends Exception {

    public ValidationException() {
    }

    public ValidationException(final String message) {
        super(message);
    }

    public ValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
