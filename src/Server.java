import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Server {

    public static void main (String args[]) throws IOException {

        ServerSocket server = new ServerSocket(12345);
        Socket socket;
        ReentrantLock lock = new ReentrantLock();
        Cloud cloud = new Cloud();

        try {
            while ((socket = server.accept()) != null) {
                Condition cond = lock.newCondition();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                Message msg = new Message(lock,cond);
                ServerIn serverIn = new ServerIn(msg,in,cloud);
                ServerOut serverOut = new ServerOut(msg,out);
                serverIn.start();
                serverOut.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
