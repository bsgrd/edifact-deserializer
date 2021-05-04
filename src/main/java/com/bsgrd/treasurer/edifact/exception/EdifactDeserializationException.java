package com.bsgrd.treasurer.edifact.exception;

public class EdifactDeserializationException extends Exception {

    public EdifactDeserializationException() {
    }

    public EdifactDeserializationException(final String message) {
        super(message);
    }

    public EdifactDeserializationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static EdifactDeserializationException fromTemplate(final String messageTemplate, Object... args) {
        return new EdifactDeserializationException(String.format(messageTemplate, args));
    }

}
