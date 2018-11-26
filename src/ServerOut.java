import java.io.PrintWriter;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ServerOut implements Runnable {

    private PrintWriter output;
    private ReentrantLock lock;
    private Condition cond;

    public ServerOut(PrintWriter output)  {
        this.output = output;
        //this.cond =
        //this.lock =
    }

    public void run() {
        this.lock.lock();
        try {
            String input;



        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.lock.unlock();
        }
    }
}
