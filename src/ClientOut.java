import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ClientOut implements Runnable {

    private BufferedReader in;
    private PrintWriter writer;
    private Socket socket;
    private ReentrantLock lock;
    private Condition cond;

    public ClientOut(Socket socket, ReentrantLock lock, Condition cond) {
        try {
            this.socket = socket;
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

    }
}
