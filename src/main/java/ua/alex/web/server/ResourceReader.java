package ua.alex.web.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceReader {

    private String resourcePath;

    public ResourceReader(String resourcePath) {
        if (resourcePath == null || !(Paths.get(resourcePath).isAbsolute())) {
            throw new IllegalArgumentException("resourcePath cannot be null!");
        }
        this.resourcePath = resourcePath;
    }

    public BufferedReader getResource(String url) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("resourcePath cannot be null!");
        }
        Path pathToResource = Paths.get(resourcePath, url);
        if (Files.notExists(pathToResource)) {
            throw new IOException("Resource given in URL does not exists!");
        }
        return Files.newBufferedReader(pathToResource);
    }
}
