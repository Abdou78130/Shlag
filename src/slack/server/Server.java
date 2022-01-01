package slack.server;

import slack.model.Message;
import slack.service.UserService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("Creating a server on port 1236");
        try(ServerSocket serverSocket = new ServerSocket(1236)) {
            while(true) {
                Socket socket = serverSocket.accept();
                //clientList.add(new ConnectionToClient(socket));
                pool.submit(() -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                    String ligne = reader.readLine();
                    String reponse =ligne+" s'est connecté au Channel : !";
                    writer.println(reponse);
                    //ChannelService cs= new ChannelService();
                    //cs.userRepository.select();
                    //System.out.println(reponse+" s'est connecté au Channel : !");
                    //MessageService ms = new MessageService(); TODO
                    boolean t = true;
                    while(t) {
                        ligne = reader.readLine();
                        reponse = ligne;
                       // for(ConnectionToClient client : clientList) {
                            System.out.println(reponse);
                            writer.println(reponse);
                        //}
                        //ms.saveMessage(); TODO fct qui sauvegarde le message dans la base de donnée
                    }
                    return true;
                });
            }
        } finally {
            pool.shutdown();
        }
    }
}
