package com.bsgrd.treasurer.edifact.dto;

import java.util.List;

public class EdifactFile {
    private final ServiceSegment serviceSegment;
    private final Segment header;
    private final List<List<Segment>> messages;
    private final List<Segment> footerSegments;

    public EdifactFile(final ServiceSegment serviceSegment,
                       final Segment header,
                       final List<List<Segment>> messages,
                       final List<Segment> footerSegments) {
        this.serviceSegment = serviceSegment;
        this.header = header;
        this.messages = messages;
        this.footerSegments = footerSegments;
    }

    public ServiceSegment getServiceSegment() {
        return serviceSegment;
    }

    public Segment getHeader() {
        return header;
    }

    public List<List<Segment>> getMessages() {
        return messages;
    }

    public List<Segment> getFooterSegments() {
        return footerSegments;
    }
}
