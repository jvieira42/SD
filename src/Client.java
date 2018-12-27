import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Client {

    public static void main (String args[]) throws InterruptedException{
        ReentrantLock lock = new ReentrantLock();
        Condition cond = lock.newCondition();

        try {
            Socket socket = new Socket("localhost",12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Menu menu = new Menu();

            ClientIn clientIn = new ClientIn(socket, lock, cond, menu);
            ClientOut clientOut = new ClientOut(in, menu, lock, cond);

            clientIn.start();
            clientOut.start();
            clientIn.join();
            clientOut.join();

            in.close();
            System.out.println("Closing client connection\n");
            socket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
