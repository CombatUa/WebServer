package ua.alex.web.server;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class RequestParserTest {

    @Test
    void parseRequest() throws IOException {
        String httpRequest = "GET /index.html HTTP/1.1" +"\r"+ "Content-Length: 22254";
        Request request = RequestParser.parseRequest(new BufferedReader(new StringReader(httpRequest)));
        assertAll("Request", () -> assertEquals(HttpMethod.GET, request.getMethod()), () -> assertEquals("/index.html", request.getUrl()));
    }

}