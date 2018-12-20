import java.io.BufferedReader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ClientIn extends Thread {

    private BufferedReader in;
    private Menu menu;
    private ReentrantLock lock;
    private Condition cond;

    public ClientIn(BufferedReader in, Menu menu, ReentrantLock lock, Condition cond) {
        this.in = in;
        this.menu = menu;
        this.lock = lock;
        this.cond = cond;
    }

    public void run() {
        String serverIn;
        try {
            while((serverIn = in.readLine()) != null){





            }






        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
