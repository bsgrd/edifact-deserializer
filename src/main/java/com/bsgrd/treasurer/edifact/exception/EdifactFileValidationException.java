package com.bsgrd.treasurer.edifact.exception;

public class EdifactFileValidationException extends ValidationException {

    public EdifactFileValidationException() {
    }

    public EdifactFileValidationException(final String message) {
        super(message);
    }

    public EdifactFileValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static EdifactFileValidationException fromTemplate(final String messageTemplate, final Object...args) {
        return new EdifactFileValidationException(String.format(messageTemplate, args));
    }

    public static EdifactFileValidationException fromTemplate(final String messageTemplate, final Throwable cause, Object...args) {
        return new EdifactFileValidationException(String.format(messageTemplate, args), cause);
    }

}
