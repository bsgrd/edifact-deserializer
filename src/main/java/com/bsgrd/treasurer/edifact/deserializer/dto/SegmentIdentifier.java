package com.bsgrd.treasurer.edifact.deserializer.dto;

public enum SegmentIdentifier {
    SERVICE_SEGMENT("UNA");

    private final String identifier;

    SegmentIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
