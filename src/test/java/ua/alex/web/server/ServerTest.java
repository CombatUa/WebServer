package ua.alex.web.server;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Paths;
@Disabled
class ServerTest {
    static private int portNumber;
    private static String resourcePath;

    @BeforeAll
    @Disabled
    static void init() throws IOException {
        System.out.println("start");
        portNumber = 3000;
        resourcePath = (Paths.get("target/test-classes").toAbsolutePath().toString());
        new Thread(()->
        Server.main(new String[]{"3000",resourcePath}));

    }

    @Test
    @Disabled
    void start() throws IOException, InterruptedException {

        Socket socket = new Socket("120.0.0.1", portNumber);
        System.out.println("connect");
        socket.close();
//        assertThrows (IOException.class,()->new Socket("localhost", portNumber ));

    }

}