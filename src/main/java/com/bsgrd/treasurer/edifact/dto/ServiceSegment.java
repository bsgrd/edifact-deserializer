package com.bsgrd.treasurer.edifact.dto;

import com.bsgrd.treasurer.edifact.exception.EdifactFileValidationException;

import static com.bsgrd.treasurer.edifact.dto.SegmentIdentifier.SERVICE_SEGMENT;

public class ServiceSegment {
    private final String segmentIdentifier;
    private final String compositeSeparator;
    private final String dataSeparator;
    private final String decimalNotation;
    private final String escapeCharacter;
    private final String reservedCharacter;
    private final String segmentTerminator;

    public ServiceSegment(final String segmentIdentifier,
                          final String compositeDataElementSeparator,
                          final String dataElementSeparator,
                          final String decimalNotation,
                          final String escapeCharacter,
                          final String reservedCharacter,
                          final String segmentTerminator) {
        this.segmentIdentifier = segmentIdentifier;
        this.compositeSeparator = compositeDataElementSeparator;
        this.dataSeparator = dataElementSeparator;
        this.decimalNotation = decimalNotation;
        this.escapeCharacter = escapeCharacter;
        this.reservedCharacter = reservedCharacter;
        this.segmentTerminator = segmentTerminator;
    }

    public static ServiceSegment fromString(final String segmentString) throws EdifactFileValidationException {
        try {
            String segmentIdentifier = segmentString.substring(0, 3);
            String compositeDataElementSeparator = segmentString.substring(3, 4);
            String dataElementSeparator = segmentString.substring(4, 5);
            String decimalNotation = segmentString.substring(5, 6);
            String escapeCharacter = segmentString.substring(6, 7);
            String reservedCharacter = segmentString.substring(7, 8);
            String segmentSeparator = segmentString.substring(8, 9);

            if (!SERVICE_SEGMENT.getIdentifier().equals(segmentIdentifier)) {
                throw EdifactFileValidationException
                        .fromTemplate("Failed to parse service segment; Service segment identifier is not equal to %s.", SERVICE_SEGMENT);
            }

            return new ServiceSegment(
                    segmentIdentifier,
                    compositeDataElementSeparator,
                    dataElementSeparator,
                    decimalNotation,
                    escapeCharacter,
                    reservedCharacter,
                    segmentSeparator
            );
        } catch (EdifactFileValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new EdifactFileValidationException("Failed to parse service segment.", e);
        }
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

    public String getSegmentTerminator() {
        return segmentTerminator;
    }

}
