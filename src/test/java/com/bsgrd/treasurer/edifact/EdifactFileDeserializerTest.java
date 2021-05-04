package com.bsgrd.treasurer.edifact;

import com.bsgrd.treasurer.edifact.dto.EdifactFile;
import com.bsgrd.treasurer.edifact.exception.EdifactDeserializationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EdifactFileDeserializerTest {

    @Test
    void testEdifactFileDeserializer() throws IOException, EdifactDeserializationException {
        byte[] fileBytes = ClassLoader.getSystemResourceAsStream("debmul.edi").readAllBytes();
        EdifactFileDeserializer edifactFileDeserializer = new EdifactFileDeserializer();
        EdifactFile edifactFile = edifactFileDeserializer.deserialize(fileBytes);
        assertNotNull(edifactFile);
    }
}
