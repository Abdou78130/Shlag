import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        System.out.println("Creating a server on port 1236");
        try(AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress(1236));
            System.out.println("Waiting a connection...");
            Future<AsynchronousSocketChannel> future = serverSocket.accept();
            AsynchronousSocketChannel socketChannel = future.get();
            System.out.println("A client is connected from " + socketChannel.getRemoteAddress());
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.read(buffer).get();
            buffer.flip();
            System.out.println("Client has sent : \n" + new String(buffer.array()));
        }
    }
}
