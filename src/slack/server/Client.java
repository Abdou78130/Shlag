package slack.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.BufferOverflowException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import slack.model.Message;
import slack.model.User;
import slack.service.UserService;

public class Client {
    public static void main(String[] args) throws IOException {
        try(Socket socket= new Socket("localhost",1236)){
            BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            String line=args[0];
            writer.println(line);
            line = null;

            do{
                line=entree.readLine();
                line = args[0]+": "+line;
                writer.println(line);
            }while(line!=null);
        }
    }
}