package com.bsgrd.treasurer.edifact.dto;

public enum SegmentIdentifier {
    SERVICE_SEGMENT("UNA"),
    FILE_HEADER("UNB"),
    MESSAGE_HEADER("UNH"),
    LINE_HEADER("LIN"),
    SEQUENCE_HEADER("SEQ"),
    MESSAGE_FOOTER("UNT"),
    FILE_FOOTER("UNZ");

    private final String identifier;

    SegmentIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
