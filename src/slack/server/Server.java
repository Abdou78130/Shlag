package slack.server;

import java.io.IOException;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("Creating a server on port 1236");
        try(ServerSocket serverSocket = new ServerSocket(1236)) {
            System.out.println("Waiting a connection...");
            int idClient = 0;
            while(true) {
                Socket socket = serverSocket.accept();
                int myid = ++idClient;
                pool.submit(() -> {
                    System.out.println("A client is connected from " + socket.getInetAddress().getHostAddress());
                    StringWriter writer = new StringWriter();
                    //IOUtils.copy(socket.getInputStream(), writer, StandardCharsets.UTF_8);
                    System.out.println("Client " + myid + " has sent : \n" + writer);
                    return true;
                });
            }
        } finally {
            pool.shutdown();
        }
    }
}
