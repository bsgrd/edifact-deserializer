package com.bsgrd.treasurer.edifact.dto;

import java.util.Arrays;
import java.util.List;

public class DataElement {
    private final List<String> compositeData;

    private DataElement(List<String> compositeData) {
        this.compositeData = compositeData;
    }

    public static DataElement fromString(final String dataElementString, final ServiceSegment serviceSegment) {
        return new DataElement(Arrays.asList(dataElementString.split(serviceSegment.getCompositeSeparator())));
    }

    public List<String> getCompositeData() {
        return compositeData;
    }
}
