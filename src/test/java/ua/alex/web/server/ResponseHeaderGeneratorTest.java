package ua.alex.web.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseHeaderGeneratorTest {
    @Test
    void generateHeaderSuccess() {
        String response = "HTTP/1.1 200 OK\r\n";
        assertEquals(response, ResponseHeaderGenerator.generateHeader(true));
    }

    void generateHeaderNotSuccess() {
        String response = "HTTP/1.1 404 OK\r\n";
        assertEquals(response, ResponseHeaderGenerator.generateHeader(false));
    }
}