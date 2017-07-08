package ua.alex.web.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Logger;

public class RequestHandler implements Handler, Runnable {
    static Logger logger = Logger.getLogger(RequestHandler.class.getName());
    private BufferedReader reader;
    private BufferedWriter writer;
    private ResourceReader resourceReader;
    private Request request;
    private BufferedReader resourceBufferedReader;
    private String responseHeader;

    public RequestHandler(BufferedReader reader, BufferedWriter writer, ResourceReader resourceReader) {
        this.reader = reader;
        this.writer = writer;
        this.resourceReader = resourceReader;
    }

    @Override
    public void run() {
        handle();
    }

    @Override
    public void handle() {

        boolean isSuccess = true;

        try {
            request = RequestParser.parseRequest(reader);
            resourceBufferedReader = resourceReader.getResource(request.getUrl());
        } catch (IOException e) {
            logger.severe(e.getMessage());
            isSuccess = false;
        }
        responseHeader = ResponseHeaderGenerator.generateHeader(isSuccess);
        try {
            writeResponse();
        } catch (IOException e) {
            logger.severe(e.getMessage());
        } finally {
            closeQuietly(resourceBufferedReader);
            closeQuietly(writer);
            closeQuietly(reader);

        }

    }

    private void writeResponse() throws IOException {
        writer.write(responseHeader);
        writer.newLine();
        if (resourceBufferedReader != null) {
            copyStreams(resourceBufferedReader, writer);
        }

    }

    private void copyStreams(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }

    }

    private void closeQuietly(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException ioe) {
            logger.severe(ioe.getMessage());
        }
    }
}
