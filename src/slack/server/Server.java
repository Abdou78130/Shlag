package slack.server;

import slack.model.Admin;
import slack.model.Channel;
import slack.model.User;
import slack.service.ChannelService;
import slack.service.MessageService;
import slack.service.UserService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("Creating a server on port 1236");
        try (ServerSocket serverSocket = new ServerSocket(1236)) {
            while (true) {
                Socket socket = serverSocket.accept();
                pool.submit(() -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    String ligne = reader.readLine();
                    String reponse = ligne + " s'est connecté au Channel :  #general !";
                    writer.println(reponse);
                    boolean t = true;
                    while (t) {
                        ligne = reader.readLine();
                        reponse = ligne;
                        System.out.println(reponse);
                        writer.println(reponse);

                    }
                    return true;
                });
            }
        } finally {
            pool.shutdown();
        }
    }
    public static void connectionAccept(){

    }
}
