package slack.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.BufferOverflowException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import slack.model.Channel;
import slack.model.Message;
import slack.model.User;
import slack.service.UserService;

public class Client {
    public static void connectionServer(User u) throws IOException {
        try(Socket socket= new Socket("localhost",1236)){
            BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            String line=u.getUsername();
            writer.println(line);
            Client.readServ(reader);
            line = null;

            do{
                line=entree.readLine();
                line = u.getUsername()+": "+line;
                writer.println(line);
                Client.readServ(reader);

            }while(line!=null);
        }
    }
    private static void readServ(BufferedReader reader){
        try {
            String reponse = reader.readLine();
            System.out.println(reponse);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
