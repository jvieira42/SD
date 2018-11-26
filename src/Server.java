import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author joao
 */
public class Server {

    public static void main (String args[]) throws IOException {
        ServerSocket server = new ServerSocket(12345);
        Socket socket;
        ReentrantLock lock = new ReentrantLock();

        try {





        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
