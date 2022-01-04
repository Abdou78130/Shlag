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
            Channel cur_chan = ChannelService.channelRepository.select("#general");
            cur_chan.addUser(cur_us.getUserId());
            do{
                line=entree.readLine();
                line = u.getId()+": "+line;
                writer.println(line);
                Client.readServ(reader,cur_us);
            }while(line!=null);
        }
    }
    private static void readServ(BufferedReader reader, User cur_u){
        try {
            String reponse = reader.readLine();

            // join channel PROBLEME
            String[] substr = reponse.split(" ");
            String cmd = "";
            String chan = "";
            if (substr.length > 2) {
                cmd = substr[1];
                chan = substr[2];
            }
             /*//PROBLEME
            if((cur_u.getId()+": /join "+ChannelService.channelRepository.select(chan).getId()).equals(reponse)){
                System.out.println(cmd);
                System.out.println(chan);
                //Enlever user du current channel
                //cur_c=ChannelService.channelRepository.select(chan);
                reponse=ChannelService.channelRepository.select(chan).getId() + "s'est connecté au Channel : "+ChannelService.channelRepository.select(chan).getId()+" !";
                System.out.println("Changement bien effectué");
            }
              */

            // CREATION channel pas test en tant que admin
            if (cmd.equals("/create")){
                if (cur_u.getAdmin()) {
                    ChannelService.creerChannel(chan);
                    System.out.println("Channel bien créer");
                } else {
                    System.out.println("Vous n'avez pas les droits nécessaire pour cette commande");
                }
            }
            // DELETE channel pas test en tant que admin
            if(cmd.equals("/delete")) {
                if (cur_u.getAdmin()) {
                    ChannelService.supprimerChannel(chan);
                    System.out.println("Channel bien supprimer");
                } else {
                    System.out.println("Vous n'avez pas les droits nécessaire pour cette commande");
                }
            }
            if(cmd.equals("/affiche") && chan.equals("listeChannel")){
                for ( Channel c: ChannelService.channelRepository.select()){
                  System.out.println(c.getId());
                }
            }

            System.out.println(reponse);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}