package com.bsgrd.treasurer.edifact;

import com.bsgrd.treasurer.edifact.dto.EdifactFileContainer;
import com.bsgrd.treasurer.edifact.dto.Segment;
import com.bsgrd.treasurer.edifact.dto.SegmentIdentifier;
import com.bsgrd.treasurer.edifact.dto.ServiceSegment;
import com.bsgrd.treasurer.edifact.exception.ValidationException;
import com.bsgrd.treasurer.edifact.util.SplitterUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bsgrd.treasurer.edifact.dto.SegmentIdentifier.FILE_HEADER;

public class EdifactFileDeserializer {
    private static final int SERVICE_SEGMENT_LENGTH = 9;

    public EdifactFileContainer deserialize(final byte[] fileBytes) throws ValidationException {
        ServiceSegment serviceSegment = ServiceSegment.fromString(extractServiceSegment(fileBytes));
        String fileContents = extractFileContents(fileBytes);

        List<Segment> segments = extractSegments(fileContents, serviceSegment);

        return new EdifactFileContainer(serviceSegment, segments);
    }

    private String extractServiceSegment(final byte[] fileBytes) {
        byte[] serviceSegmentBytes = Arrays.copyOfRange(fileBytes, 0, SERVICE_SEGMENT_LENGTH);
        return new String(serviceSegmentBytes, Charset.defaultCharset());
    }

    private String extractFileContents(final byte[] fileBytes) {
        byte[] fileContentBytes = Arrays.copyOfRange(fileBytes, SERVICE_SEGMENT_LENGTH, fileBytes.length - SERVICE_SEGMENT_LENGTH);
        return new String(fileContentBytes, StandardCharsets.ISO_8859_1)
                .replaceAll("\n", "")
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
