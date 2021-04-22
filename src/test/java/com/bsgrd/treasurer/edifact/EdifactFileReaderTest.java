package com.bsgrd.treasurer.edifact;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EdifactFileReaderTest {

    @Test
    public void testEdifactFileReader() throws IOException {
        byte[] fileBytes = ClassLoader.getSystemResourceAsStream("debmul.edi").readAllBytes();
        EdifactFileReader edifactFileReader = new EdifactFileReader();
        String fileContents = edifactFileReader.readFile(fileBytes);
        assertNotNull(fileContents);
    }

}
