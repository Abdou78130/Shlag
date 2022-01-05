package slack.server;

import slack.model.Channel;
import slack.model.Message;
import slack.model.User;
import slack.service.ChannelService;
import slack.service.MessageService;
import slack.service.UserService;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void connectionServer(User u, Channel c) throws IOException {
        try (Socket socket = new Socket("localhost", 1236)) {
            BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String line = u.getId() + " " + c.getId();
            String[] sub = line.split(" ");
            writer.println(line);
            String reponse = reader.readLine();
            System.out.println(reponse);
            MessageService.creerMessage(reponse, c, u);
            line = null;
            User cur_us = UserService.getCurrentUser();
            Channel cur_chan = ChannelService.connexionChannel(c.getId());
            // cur_chan.addUser(cur_us.getUserId());
            do {
                line = entree.readLine();
                line = u.getId() + ": " + line;
                writer.println(line);
                Client.readServ(reader, cur_us, cur_chan);
            } while (line != null);
        }
    }

    public static void connectionServer(User u, Channel c, ByteArrayInputStream message) throws IOException {
        try (Socket socket = new Socket("localhost", 1236)) {
            BufferedReader entree = new BufferedReader(new InputStreamReader(message));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String line = u.getId() + " " + c.getId();
            String[] sub = line.split(" ");
            writer.println(line);
            String reponse = reader.readLine();
            System.out.println(reponse);
            MessageService.creerMessage(reponse, c, u);
            String line2 = null;
            User cur_us = UserService.getCurrentUser();
            Channel cur_chan = ChannelService.connexionChannel(c.getId());
            // cur_chan.addUser(cur_us.getUserId());
            /*
             * do{
             * System.out.println("GROS TEST");
             * line2=entree.readLine();
             * line = u.getId()+": "+line2;
             * writer.println(line);
             * 
             * Client.readServ(reader,cur_us,cur_chan);
             * System.out.println("GROS TEST2");
             * }while(line2!=null);
             */

            do {
                line = entree.readLine();
                line = u.getId() + ": " + line;
                writer.println(line);
                Client.readServ(reader, cur_us, cur_chan);
            } while (line != null);
        }
    }

    private static void readServ(BufferedReader reader, User cur_u, Channel cur_c) {
        try {
            String reponse = reader.readLine();

            String[] substr = reponse.split(" ");
            String cmd = "";
            String arg = "";
            if (substr.length > 2) {
                cmd = substr[1];
                arg = substr[2];
            }
            // join channel
            if (cmd.equals("/join")) {
                if (ChannelService.channelRepository.select(arg) != null) {
                    // Enlever user dans l'ancien channel
                    cur_c = ChannelService.connexionChannel(arg);
                    reponse = cur_u.getId() + " s'est connecté au Channel : " + cur_c.getId();
                    System.out.println("Changement bien effectué");
                }
            }
            // CREATION channel
            if (cmd.equals("/create")) {
                if (cur_u.getAdmin()) {
                    ChannelService.creerChannel(arg);
                    System.out.println("Channel bien créer");
                    return;
                } else {
                    System.out.println("Vous n'avez pas les droits nécessaire pour cette commande");
                    return;
                }
            }
            // DELETE channel
            if (cmd.equals("/delete")) {
                if (cur_u.getAdmin()) {
                    ChannelService.supprimerChannel(arg);
                    System.out.println("Channel bien supprimer");
                    return;
                } else {
                    System.out.println("Vous n'avez pas les droits nécessaire pour cette commande");
                    return;
                }
            }
            // modify channel name
            if (cmd.equals("/modify")) {
                if (cur_u.getAdmin()) {
                    ChannelService.modifierChannel(arg);
                    reponse = "Channel à été changer en " + arg + " par " + cur_u.getId();
                }
            }
            // Affiche la liste des channels
            if (cmd.equals("/affiche") && arg.equals("listeChannel")) {
                for (Channel c : ChannelService.channelRepository.select()) {
                    System.out.println(c.getId());
                }
                return;
            }
            // affiche message pour les tests
            if (cmd.equals("/affiche") && arg.equals("messages")) {
                for (Message m : MessageService.messageRepository.select()) {
                    System.out.println(m.getMessage());
                }
                return;
            }

            // modifier le dernier message marche pas
            if (cmd.equals("/modifyMessage")) {
                String new_mess = cur_u.getId() + ": ";
                for (int i = 2; i < substr.length; i++) {
                    new_mess += substr[i] + " ";
                }
                MessageService.modifierMessage(new_mess, MessageService.getIdLastMessage());
                return;
            }

            System.out.println(reponse);
            MessageService.creerMessage(reponse, cur_c, cur_u);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}