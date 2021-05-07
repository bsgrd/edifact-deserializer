package com.bsgrd.treasurer.edifact.deserializer.dto;

import com.bsgrd.treasurer.edifact.deserializer.util.SplitterUtils;

import java.util.List;

public class DataElement {
    private final List<String> compositeData;

    private DataElement(List<String> compositeData) {
        this.compositeData = compositeData;
    }

    public static DataElement fromString(final String dataElementString, final ServiceSegment serviceSegment) {
        List<String> compositeDataElements = SplitterUtils.split(dataElementString, serviceSegment.getCompositeSeparator(), serviceSegment.getEscapeCharacter());
        return new DataElement(compositeDataElements);
    }

    public List<String> getCompositeData() {
        return this.compositeData;
    }
}
