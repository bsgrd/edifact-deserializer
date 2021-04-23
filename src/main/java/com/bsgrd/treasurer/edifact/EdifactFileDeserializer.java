package com.bsgrd.treasurer.edifact;

import com.bsgrd.treasurer.edifact.dto.Segment;
import com.bsgrd.treasurer.edifact.dto.ServiceSegment;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EdifactFileReader {
    private static final int SERVICE_SEGMENT_LENGTH = 9;

    public String readFile(final byte[] fileBytes) {
        ServiceSegment serviceSegment = ServiceSegment.fromString(extractServiceSegment(fileBytes));
        String fileContents = extractFileContents(fileBytes);

        String[] segmentStrings = fileContents.split(serviceSegment.getSegmentSeparator());
        List<Segment> segments = new ArrayList<>();

        for (String segmentString : segmentStrings) {
            segments.add(Segment.fromString(segmentString, serviceSegment));
        }

        return fileContents;
    }

    private String extractServiceSegment(final byte[] fileBytes) {
        byte[] serviceSegmentBytes = Arrays.copyOfRange(fileBytes, 0, SERVICE_SEGMENT_LENGTH);
        return new String(serviceSegmentBytes, Charset.defaultCharset());
    }

    private String extractFileContents(final byte[] fileBytes) {
        byte[] fileContentBytes = Arrays.copyOfRange(fileBytes, SERVICE_SEGMENT_LENGTH, fileBytes.length - SERVICE_SEGMENT_LENGTH);
        return new String(fileContentBytes, Charset.defaultCharset())
                .replaceAll("\n", "")
                .replace("\r", "");
    }

}
