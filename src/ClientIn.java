import java.io.BufferedReader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ClientIn implements Runnable {

    private BufferedReader in;
    private ReentrantLock lock;
    private Condition cond;

    public ClientIn(BufferedReader in, ReentrantLock lock, Condition cond) {
        this.in = in;
        this.lock = lock;
        this.cond = cond;
    }

    public void run() {
        String input;
        try {






        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
