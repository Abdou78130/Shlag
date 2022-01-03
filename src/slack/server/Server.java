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
                ChannelService.creerChannel("#general");
                ChannelService.creerChannel("#ProjetShlag");
                ChannelService.creerChannel("#Cours");
                Socket socket = serverSocket.accept();
                User cur_us =UserService.getCurrentUser();
                Channel cur_chan = ChannelService.channelRepository.select("#general");
                //cur_chan.addUser(cur_us.getUserId()); //PROBLEME
                pool.submit(() -> {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    String ligne = reader.readLine();
                    String cur_user=ligne;

                    //System.out.println(cur_us.getId());
                    String reponse = ligne + " s'est connecté au Channel : " + cur_chan.getId() + " !";
                    writer.println(reponse);
                    String chan= null;
                    boolean t = true;
                    while (t) {
                        ligne = reader.readLine();
                        reponse = ligne;

                        // join channel PROBLEME
                        chan=ligne.substring(7); //On obtient le nom du channel
                        if(("/join "+ChannelService.channelRepository.select(chan)).equals(reponse)){
                            //Enlever user du current channel
                            //cur_chan=ChannelService.channelRepository.select(chan); //PROBLEME
                            reponse=ChannelService.channelRepository.select(chan).getId() + "s'est connecté au Channel : "+ChannelService.channelRepository.select(chan).getId()+" !";
                            continue;
                        }

                        //CREATION CHANNEL pas test
                        chan=ligne.substring(0,9);
                        if((chan).equals("/create ")){
                            if(cur_us instanceof Admin){
                                chan=ligne.substring(9); //on prend le nom du channel tapé
                                ChannelService.creerChannel(chan);
                                continue;
                            }
                        }

                        //CREATION CHANNEL pas test
                        chan=ligne.substring(0,9);
                        if((chan).equals("/delete ")){
                            if(cur_us instanceof Admin){
                                chan=ligne.substring(9); //on prend le nom du channel tapé
                                ChannelService.supprimerChannel(chan);
                                continue;
                            }
                        }
                        System.out.println(reponse);
                        writer.println(reponse);
                        //PROBLEME for
                        for (User u: UserService.userRepository.select()) {
                            if(u.getId().equals(cur_user)){
                                MessageService.creerMessage(reponse,cur_chan,u);
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
