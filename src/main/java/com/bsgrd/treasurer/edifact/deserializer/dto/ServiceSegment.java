package com.bsgrd.treasurer.edifact.deserializer.dto;

import com.bsgrd.treasurer.edifact.deserializer.exception.EdifactDeserializationException;

import static com.bsgrd.treasurer.edifact.deserializer.dto.SegmentIdentifier.SERVICE_SEGMENT;

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

    public static ServiceSegment fromString(final String segmentString) throws EdifactDeserializationException {
        try {
            String segmentIdentifier = segmentString.substring(0, 3);
            String compositeDataElementSeparator = segmentString.substring(3, 4);
            String dataElementSeparator = segmentString.substring(4, 5);
            String decimalNotation = segmentString.substring(5, 6);
            String escapeCharacter = segmentString.substring(6, 7);
            String reservedCharacter = segmentString.substring(7, 8);
            String segmentSeparator = segmentString.substring(8, 9);

            if (!SERVICE_SEGMENT.getIdentifier().equals(segmentIdentifier)) {
                String messageTemplate = "Failed to parse service segment; Service segment identifier is not equal to %s.";
                throw EdifactDeserializationException.fromTemplate(messageTemplate, SERVICE_SEGMENT);
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
        } catch (EdifactDeserializationException e) {
            throw e;
        } catch (Exception e) {
            throw new EdifactDeserializationException("Failed to parse service segment.", e);
        }
    }

    public String getSegmentIdentifier() {
        return this.segmentIdentifier;
    }

    public String getCompositeSeparator() {
        return this.compositeSeparator;
    }

    public String getDataSeparator() {
        return this.dataSeparator;
    }

    public String getDecimalNotation() {
        return this.decimalNotation;
    }

    public String getEscapeCharacter() {
        return this.escapeCharacter;
    }

    public String getReservedCharacter() {
        return this.reservedCharacter;
    }

    public String getSegmentTerminator() {
        return this.segmentTerminator;
    }

}
