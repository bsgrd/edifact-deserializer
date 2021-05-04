package com.bsgrd.treasurer.edifact;

import com.bsgrd.treasurer.edifact.dto.DataElement;
import com.bsgrd.treasurer.edifact.dto.EdifactFile;
import com.bsgrd.treasurer.edifact.dto.Segment;
import com.bsgrd.treasurer.edifact.dto.ServiceSegment;
import com.bsgrd.treasurer.edifact.exception.EdifactDeserializationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EdifactFileDeserializerTest {
    EdifactFileDeserializer edifactFileDeserializer = new EdifactFileDeserializer();

    @Test
    void testEdifactFileDeserializer() throws IOException, EdifactDeserializationException {
        byte[] fileBytes = ClassLoader.getSystemResourceAsStream("cremul.edi").readAllBytes();

        EdifactFile edifactFile = this.edifactFileDeserializer.deserialize(fileBytes);
        assertNotNull(edifactFile);
        verifyServiceSegment(edifactFile.getServiceSegment());

        List<Segment> segments = edifactFile.getSegments();
        assertEquals(28, segments.size());
        assertEquals(11, segments.get(0).getDataElements().size());
    }

    @Test
    void testEdifactFileDeserializerEscapedFile() throws IOException, EdifactDeserializationException {
        byte[] fileBytes = ClassLoader.getSystemResourceAsStream("cremul_escaped.edi").readAllBytes();

        EdifactFile edifactFile = this.edifactFileDeserializer.deserialize(fileBytes);
        assertNotNull(edifactFile);
        verifyServiceSegment(edifactFile.getServiceSegment());

        List<Segment> segments = edifactFile.getSegments();

        Segment nameAddressSegment = segments.stream()
                .filter(s -> "NAD".equals(s.getSegmentIdentifier()))
                .findFirst().orElseThrow();
        List<DataElement> nameAddressDataElements = nameAddressSegment.getDataElements();
        assertEquals(5, nameAddressDataElements.size());
        List<String> nameAddressCompositeDataElements = nameAddressDataElements.get(4).getCompositeData();
        assertEquals("Company A/S", nameAddressCompositeDataElements.get(0));
        assertEquals("Hovedgaden 630", nameAddressCompositeDataElements.get(1));
        assertEquals("DK-9999 CITY", nameAddressCompositeDataElements.get(2));

        Segment freeTextSegment = segments.stream()
                .filter(s -> "FTX".equals(s.getSegmentIdentifier()))
                .findFirst().orElseThrow();

        List<DataElement> freeTextDataElements = freeTextSegment.getDataElements();
        assertEquals(5, freeTextDataElements.size());

        DataElement dataElement = freeTextDataElements.get(4);
        List<String> compositeData = dataElement.getCompositeData();
        assertEquals(2, compositeData.size());

        assertEquals("Company A/S,DK,Invoice-GEBYR:", compositeData.get(0));
        assertEquals("EndToEndID:5600149008", compositeData.get(1));
    }

    @Test
    void testEdifactFileDeserializerWithError() throws IOException {
        byte[] fileBytes = ClassLoader.getSystemResourceAsStream("cremul_invalid_service_segment.edi").readAllBytes();
        assertThrows(EdifactDeserializationException.class, () -> this.edifactFileDeserializer.deserialize(fileBytes));
    }

    private void verifyServiceSegment(final ServiceSegment serviceSegment) {
        assertEquals("UNA", serviceSegment.getSegmentIdentifier());
        assertEquals(":", serviceSegment.getCompositeSeparator());
        assertEquals("+", serviceSegment.getDataSeparator());
        assertEquals(",", serviceSegment.getDecimalNotation());
        assertEquals("?", serviceSegment.getEscapeCharacter());
        assertEquals(" ", serviceSegment.getReservedCharacter());
        assertEquals("'", serviceSegment.getSegmentTerminator());
    }

}
