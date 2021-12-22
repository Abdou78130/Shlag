package slack.server;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public void connection() throws IOException {
        try(Socket socket= new Socket("localhost",1236)){
            socket.getOutputStream().write("salut".getBytes(StandardCharsets.UTF_8));
        }
    }
}
