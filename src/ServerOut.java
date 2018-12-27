import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ServerOut extends Thread {

    private ReentrantLock lock;
    private Condition cond;
    private PrintWriter out;
    private BufferedReader in;
    private Message msg;


    public ServerOut(BufferedReader in, ReentrantLock lock, Condition cond, PrintWriter out, Message msg)  {
        this.lock = lock;
        this.cond = cond;
        this.out = out;
        this.in = in;
        this.msg = msg;
    }

    public void run() {
        this.lock.lock();
        try {
            String line;
            while(true) {
                line = msg.getMessage();
                if (line.equals("Error") || line.equals("logout") || line.equals("User already registered") || line.equals("quit")) {
                    break;
                }
            out.println(line);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.lock.unlock();
        }
    }
}
