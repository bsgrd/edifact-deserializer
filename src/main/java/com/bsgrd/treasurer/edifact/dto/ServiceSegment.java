package com.bsgrd.treasurer.edifact.dto;

public class ServiceSegment {
    private final String segmentIdentifier;
    private final String compositeSeparator;
    private final String dataSeparator;
    private final String decimalNotation;
    private final String escapeCharacter;
    private final String reservedCharacter;
    private final String segmentSeparator;

    public ServiceSegment(final String segmentIdentifier,
                          final String compositeSeparator,
                          final String dataSeparator,
                          final String decimalNotation,
                          final String escapeCharacter,
                          final String reservedCharacter,
                          final String segmentSeparator) {
        this.segmentIdentifier = segmentIdentifier;
        this.compositeSeparator = compositeSeparator;
        this.dataSeparator = dataSeparator;
        this.decimalNotation = decimalNotation;
        this.escapeCharacter = escapeCharacter;
        this.reservedCharacter = reservedCharacter;
        this.segmentSeparator = segmentSeparator;
    }

    public static ServiceSegment fromString(final String segmentString) {
        return new ServiceSegment(
                segmentString.substring(0, 3),
                segmentString.substring(3, 4),
                segmentString.substring(4, 5),
                segmentString.substring(5, 6),
                segmentString.substring(6, 7),
                segmentString.substring(7, 8),
                segmentString.substring(8, 9)
        );
    }

    public String getSegmentIdentifier() {
        return segmentIdentifier;
    }

    public String getCompositeSeparator() {
        return compositeSeparator;
    }

    public String getDataSeparator() {
        return dataSeparator;
    }

    public String getDecimalNotation() {
        return decimalNotation;
    }

    public String getEscapeCharacter() {
        return escapeCharacter;
    }

    public String getReservedCharacter() {
        return reservedCharacter;
    }

    public String getSegmentSeparator() {
        return segmentSeparator;
    }

}
