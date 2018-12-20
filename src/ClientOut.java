import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ClientOut extends Thread {

    private BufferedReader in;
    private PrintWriter writer;
    private Socket socket;
    private Menu menu;
    private ReentrantLock lock;
    private Condition cond;

    public ClientOut(Socket socket, Menu menu, ReentrantLock lock, Condition cond) {
        try {

            this.socket = socket;
            this.menu = menu;
            this.in = new BufferedReader(new InputStreamReader(System.in));
            this.writer = new PrintWriter(socket.getOutputStream(),true);
            this.lock = lock;
            this.cond = cond;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        String systemIn;
        try {
            menu.openMenu();
            while((systemIn = in.readLine()) != null){





            }


        socket.shutdownOutput();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
