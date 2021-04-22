package com.bsgrd.treasurer.edifact.dto;

import java.util.ArrayList;
import java.util.List;

public class Segment {
    private final String segmentIdentifier;
    private final List<DataElement> dataElements;

    public Segment(String segmentIdentifier, List<DataElement> dataElements) {
        this.segmentIdentifier = segmentIdentifier;
        this.dataElements = dataElements;
    }

    public static Segment fromString(final String segmentString, final ServiceSegment serviceSegment) {
        String[] dataElementStrings = segmentString.split(serviceSegment.getDataSeparator());
        List<DataElement> dataElements = new ArrayList<>();
        for (String dataElementString : dataElementStrings) {
            dataElements.add(DataElement.fromString(dataElementString, serviceSegment));
        }
        return new Segment(dataElementStrings[0], dataElements);
    }

    public String getSegmentIdentifier() {
        return segmentIdentifier;
    }

    public List<DataElement> getDataElements() {
        return dataElements;
    }

}
