package com.bsgrd.treasurer.edifact.dto;

public class EdifactFileHeader {
    private final Segment segment;

    private EdifactFileHeader(final Segment segment) {
        this.segment = segment;
    }

    public static EdifactFileHeader fromSegment(final Segment segment) {
        return new EdifactFileHeader(segment);
    }

    public Segment getSegment() {
        return segment;
    }
}
