package slack.server;

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
        ChannelService cs= new ChannelService();
        cs.creerChannel("#general");
        Channel general = cs.channelRepository.select("#general");
        MessageService ms= new MessageService();
        System.out.println("Creating a server on port 1236");
        try (ServerSocket serverSocket = new ServerSocket(1236)) {
            ChannelService.creerChannel("#general");
            Channel general = ChannelService.channelRepository.select("#general");
            List<User> list_user = new ArrayList<User>();
            ChannelService.creerChannel("#ProjetShlag");
            ChannelService.creerChannel("#Cours");
            while (true) {
                Socket socket = serverSocket.accept();
                pool.submit(() -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    String ligne = reader.readLine();
                    list_user.add(UserService.userRepository.select(ligne));
                    User current_user=UserService.userRepository.select(ligne);
                    String reponse = ligne + " s'est connecté au Channel : " + general.getId() + " !";
                    writer.println(reponse);
                    String chan= null;
                    boolean t = true;
                    while (t) {
                        ligne = reader.readLine();
                        reponse = ligne;
/*
                        chan=ligne.substring(7);
                        if(("/join "+ChannelService.channelRepository.select(chan)).equals(reponse)){
                            general=ChannelService.channelRepository.select(chan);
                            System.out.println(chan);
                            break;
                        }
 */
                        System.out.println(reponse);
                        writer.println(reponse);
                        MessageService.creerMessage(reponse,general,current_user);
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
