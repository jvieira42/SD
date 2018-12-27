import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Client {

    public static void main (String args[]) {
        try {
            Socket socket = new Socket("localhost",12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Menu menu = new Menu();
            menu.setOption(1);
            ReentrantLock lock = new ReentrantLock();
            Condition cond = lock.newCondition();

            ClientOut clientOut = new ClientOut(socket, menu, lock, cond);
            ClientIn clientIn = new ClientIn(in, menu, lock, cond);

            clientOut.start();
            clientIn.start();
            clientOut.join();
            clientIn.join();

            in.close();
            System.out.println("Closing client connection\n");
            socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
