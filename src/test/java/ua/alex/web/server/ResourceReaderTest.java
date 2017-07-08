package ua.alex.web.server;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourceReaderTest {
    @Test
    void getResourceTrue() throws IOException {
        String firstString = "<!DOCTYPE html>";
        ResourceReader resourceReader = new ResourceReader((Paths.get("target/test-classes").toAbsolutePath().toString()));
        BufferedReader bufferedReader = resourceReader.getResource("index.html");
        assertEquals(firstString, bufferedReader.readLine());
        bufferedReader.close();
    }
    @Test
    void getResourceFalse() throws IOException {
        ResourceReader resourceReader = new ResourceReader((Paths.get("target/test-classes").toAbsolutePath().toString()));
        assertThrows(IOException.class,()->resourceReader.getResource("index.html2"));
    }
}