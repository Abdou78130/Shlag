package slack.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.BufferOverflowException;
import java.nio.charset.StandardCharsets;
import java.util.List;


import slack.model.*;
import slack.service.*;

public class Client {
    public static void connectionServer(User u) throws IOException {
        try(Socket socket= new Socket("localhost",1236)){
            BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            String line=u.getId();
            writer.println(line);
            String reponse=reader.readLine();
            System.out.println(reponse);
            line = null;

            User cur_us = UserService.getCurrentUser();
            Channel cur_chan = ChannelService.ConnexionChannel("#general");
            //cur_chan.addUser(cur_us.getUserId());
            do{
                line=entree.readLine();
                line = u.getId()+": "+line;
                writer.println(line);
                Client.readServ(reader,cur_us,cur_chan);
            }while(line!=null);
        }
    }
    private static void readServ(BufferedReader reader, User cur_u, Channel cur_c){
        try {
            String reponse = reader.readLine();

            String[] substr = reponse.split(" ");
            String cmd = "";
            String chan = "";
            if (substr.length > 2) {
                cmd = substr[1];
                chan = substr[2];
            }
            // join channel PROBLEME
            if(cmd.equals("/join")){
                if(ChannelService.channelRepository.select(chan)!=null) {
                    //Enlever user ddans l'ancien channel
                    cur_c=ChannelService.ConnexionChannel(chan);
                    reponse = cur_u.getId()+ " s'est connecté au Channel : "+cur_c.getId();
                    System.out.println("Changement bien effectué");
                }
            }
            // CREATION channel
            if (cmd.equals("/create")){
                if (cur_u.getAdmin()) {
                    ChannelService.creerChannel(chan);
                    System.out.println("Channel bien créer");
                    return;
                } else {
                    System.out.println("Vous n'avez pas les droits nécessaire pour cette commande");
                    return;
                }
            }
            // DELETE channel
            if(cmd.equals("/delete")) {
                if (cur_u.getAdmin()) {
                    ChannelService.supprimerChannel(chan);
                    System.out.println("Channel bien supprimer");
                    return;
                } else {
                    System.out.println("Vous n'avez pas les droits nécessaire pour cette commande");
                    return;
                }
            }
            //Affiche la liste des channels
            if(cmd.equals("/affiche") && chan.equals("listeChannel")){
                for ( Channel c: ChannelService.channelRepository.select()){
                  System.out.println(c.getId());
                  return;
                }
            }
            System.out.println(reponse);
            MessageService.creerMessage(reponse,cur_c,cur_u);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}