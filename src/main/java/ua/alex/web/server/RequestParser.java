package ua.alex.web.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

public class RequestParser {
    static Logger logger = Logger.getLogger(RequestParser.class.getName());

    public static Request parseRequest(BufferedReader requestReader) throws IOException {
        Request request = new Request();

        enrichWithUrlAndMethod(request, requestReader);
        enrichWithHeaders(request, requestReader);
        logger.info(request.toString());
        return request;
    }

    private static void enrichWithUrlAndMethod(Request request, BufferedReader reader) throws IOException {
        String line;
        if ((line = reader.readLine()) == null) {
            throw new IOException("Input is null!");
        }

        logger.info("Request line:" + line);
        String[] splittedRequestLine = line.split(" ");
        request.setMethod(HttpMethod.valueOf(splittedRequestLine[0]));
        request.setUrl(splittedRequestLine[1]);
    }

    private static void enrichWithHeaders(Request request, BufferedReader reader) throws IOException {

        String headerLine;
        while ((headerLine = reader.readLine()) != null && !("".equals(headerLine))) {
            logger.info("Header line:" + headerLine);
            String[] splittedHeaderLine = headerLine.split(":");
            logger.info(headerLine);
            request.addHeader(splittedHeaderLine[0], splittedHeaderLine[1]);
        }
    }

}
