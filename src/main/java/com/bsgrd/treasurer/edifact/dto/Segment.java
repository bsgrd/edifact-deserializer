package com.bsgrd.treasurer.edifact.dto;

import com.bsgrd.treasurer.edifact.util.SplitterUtils;

import java.util.ArrayList;
import java.util.List;

public class Segment {
    private final String segmentIdentifier;
    private final List<DataElement> dataElements;

    public Segment(final String segmentIdentifier, final List<DataElement> dataElements) {
        this.segmentIdentifier = segmentIdentifier;
        this.dataElements = dataElements;
    }

    public static Segment fromString(final String segmentString, final ServiceSegment serviceSegment) {
        List<String> dataElementStrings = SplitterUtils.split(segmentString,
                serviceSegment.getDataSeparator(),
                serviceSegment.getEscapeCharacter());
        List<DataElement> dataElements = new ArrayList<>();
        for (String dataElementString : dataElementStrings) {
            dataElements.add(DataElement.fromString(dataElementString, serviceSegment));
        }
        return new Segment(dataElementStrings.get(0), dataElements);
    }

    public String getSegmentIdentifier() {
        return this.segmentIdentifier;
    }

    public List<DataElement> getDataElements() {
        return this.dataElements;
    }

}
