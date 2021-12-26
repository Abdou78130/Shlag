package slack.server;

import slack.model.Message;
import slack.service.UserService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void runServer() throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("Creating a server on port 1236");
        try(ServerSocket serverSocket = new ServerSocket(1236)) {
            while(true) {
                Socket socket = serverSocket.accept();
                pool.submit(() -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String ligne = reader.readLine();
                    String reponse =ligne;
                    System.out.println(reponse+" s'est connecté !");
                    //MessageService ms = new MessageService(); TODO
                    while(true) {
                        ligne = reader.readLine();
                        reponse = ligne;
                        System.out.println(reponse);
                        //ms.saveMessage(); TODO fct qui sauvegarde le message dans la base de donnée
                    }
                });
            }
        } finally {
            pool.shutdown();
        }
    }
}
