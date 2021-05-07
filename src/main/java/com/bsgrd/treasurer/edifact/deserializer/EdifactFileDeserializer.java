package com.bsgrd.treasurer.edifact.deserializer;

import com.bsgrd.treasurer.edifact.deserializer.dto.EdifactFile;
import com.bsgrd.treasurer.edifact.deserializer.dto.Segment;
import com.bsgrd.treasurer.edifact.deserializer.dto.ServiceSegment;
import com.bsgrd.treasurer.edifact.deserializer.exception.EdifactDeserializationException;
import com.bsgrd.treasurer.edifact.deserializer.util.SplitterUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EdifactFileDeserializer {
    private static final int SERVICE_SEGMENT_LENGTH = 9;
    private ServiceSegment serviceSegment;

    public EdifactFileDeserializer() {
    }

    public EdifactFileDeserializer(final ServiceSegment serviceSegment) {
        this.serviceSegment = serviceSegment;
    }

    public EdifactFile deserialize(final byte[] fileBytes) throws EdifactDeserializationException {
        if (this.serviceSegment == null) {
            this.serviceSegment = ServiceSegment.fromString(extractServiceSegmentString(fileBytes));
        }

        String fileContents = extractFileContents(fileBytes);

        List<Segment> segments = extractSegments(fileContents, this.serviceSegment);

        return new EdifactFile(this.serviceSegment, segments);
    }

    private String extractServiceSegmentString(final byte[] fileBytes) {
        byte[] serviceSegmentBytes = Arrays.copyOfRange(fileBytes, 0, SERVICE_SEGMENT_LENGTH);
        return new String(serviceSegmentBytes, StandardCharsets.ISO_8859_1);
    }

    private String extractFileContents(final byte[] fileBytes) {
        byte[] fileContentBytes = Arrays.copyOfRange(fileBytes, SERVICE_SEGMENT_LENGTH, fileBytes.length - SERVICE_SEGMENT_LENGTH);
        return new String(fileContentBytes, StandardCharsets.ISO_8859_1)
                .replace("\n", "")
                .replace("\r", "");
    }

    private List<Segment> extractSegments(final String fileContents, final ServiceSegment serviceSegment) {
        List<String> segmentStrings = SplitterUtils.split(fileContents,
                serviceSegment.getSegmentTerminator(),
                serviceSegment.getEscapeCharacter());

        List<Segment> segments = new ArrayList<>();

        for (String segmentString : segmentStrings) {
            segments.add(Segment.fromString(segmentString, serviceSegment));
        }

        return segments;
    }

}
