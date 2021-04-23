package com.bsgrd.treasurer.edifact.dto;

import java.util.List;

public class EdifactFileContainer {
    private final ServiceSegment serviceSegment;
    private final List<Segment> segments;

    public EdifactFileContainer(final ServiceSegment serviceSegment, final List<Segment> segments) {
        this.serviceSegment = serviceSegment;
        this.segments = segments;
    }

    public ServiceSegment getServiceSegment() {
        return serviceSegment;
    }

    public List<Segment> getSegments() {
        return segments;
    }

}