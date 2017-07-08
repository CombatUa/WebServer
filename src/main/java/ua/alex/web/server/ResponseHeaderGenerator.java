package ua.alex.web.server;

public class ResponseHeaderGenerator {
   static String generateHeader(boolean isSuccess) {
        String response;
        if (isSuccess) {
            response = "HTTP/1.1 200 OK\r\n";
        } else {
            response = "HTTP/1.1 404 OK\r\n";
        }
        return response;
    }
}
