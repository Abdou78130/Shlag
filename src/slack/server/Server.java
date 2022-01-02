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
        System.out.println("Creating a server on port 1236");
        try (ServerSocket serverSocket = new ServerSocket(1236)) {
            while (true) {
                ChannelService.creerChannel("#general");
                Channel general = ChannelService.channelRepository.select("#general");
                List<String> list_user = new ArrayList<String>();
                ChannelService.creerChannel("#ProjetShlag");
                ChannelService.creerChannel("#Cours");
                Socket socket = serverSocket.accept();


                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                pool.submit(() -> {
                    String ligne = reader.readLine();
                    list_user.add(ligne);
                    String cur_user=ligne;
                    String reponse = ligne + " s'est connecté au Channel : " + general.getId() + " !";
                    writer.println(reponse);
                    String chan= null;
                    boolean t = true;
                    while (t) {
                        ligne = reader.readLine();
                        reponse = ligne;
                        /* join channel PROBLEME
                        chan=ligne.substring(7); //On obtient le nom du channel
                        if(("/join "+ChannelService.channelRepository.select(chan)).equals(reponse)){
                            general=ChannelService.channelRepository.select(chan);
                            System.out.println(chan);
                            break;
                        }
                        */
                        System.out.println(reponse);
                        writer.println(reponse);
                        for (User u: UserService.userRepository.select()) {
                            if(u.getId().equals(cur_user)){
                                MessageService.creerMessage(reponse,general,u);
                                System.out.println("message bien creer");
                                break;
                            }
                        }
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
