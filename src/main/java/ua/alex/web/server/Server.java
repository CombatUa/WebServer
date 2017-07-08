package ua.alex.web.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {

    static Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("Please use with <port_number> <resource_path>");
            return;
        }
        int portNumber;


        if (Integer.signum(portNumber = Integer.parseInt(args[0])) != 1) {
            System.out.println("Please use positive number for <port_number>!");
            return;
        }

        String resourcePath = args[1];

        if (!Paths.get(resourcePath).toFile().isDirectory()) {
            System.out.println("Please use existing directory for <resource_path>");
            return;
        }

        Server server = new Server();
        try {
            server.start(portNumber, resourcePath);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }

    }

    public void start(int portNumber, String resourcePath) throws IOException {
        logger.info("Starting Server");
        ServerSocket serverSocket = new ServerSocket(portNumber);

        ExecutorService executorService = Executors.newCachedThreadPool();

        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ResourceReader resourceReader = new ResourceReader(resourcePath);
            logger.info("Client accepted");
            executorService.submit(new RequestHandler(bufferedReader, bufferedWriter, resourceReader));
        }

    }

    public void setResourcePath(String path) {
    }
}
