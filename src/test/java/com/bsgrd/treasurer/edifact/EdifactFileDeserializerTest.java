package com.bsgrd.treasurer.edifact;

import com.bsgrd.treasurer.edifact.dto.EdifactFile;
import com.bsgrd.treasurer.edifact.dto.EdifactFileContainer;
import com.bsgrd.treasurer.edifact.dto.Segment;
import com.bsgrd.treasurer.edifact.exception.ValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EdifactFileDeserializerTest {

    @Test
    void testEdifactFileDeserializer() throws IOException, ValidationException {
        byte[] fileBytes = ClassLoader.getSystemResourceAsStream("debmul.edi").readAllBytes();
        EdifactFileDeserializer edifactFileDeserializer = new EdifactFileDeserializer();
        EdifactFileContainer edifactFile = edifactFileDeserializer.deserialize(fileBytes);
        assertNotNull(edifactFile);
    }

    @Test
    void testStuff() {
        byte[] bytes = "\n".getBytes();
        byte[] cr = "\r".getBytes();
        byte[] unb = "UNB".getBytes();
        assertNotNull(bytes);
    }

}
