import java.io.PrintWriter;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ServerOut extends Thread {

    private PrintWriter out;
    private Message msg;
    private ReentrantLock lock;
    private Condition cond;

    public ServerOut(Message msg, PrintWriter out)  {
        this.msg = msg;
        this.out = out;
        this.lock = msg.getLock();
        this.cond = msg.getCondition();
    }

    public void run() {
        this.lock.lock();
        String line;
        try {

            while(true) {
                while((line = msg.getMessage()) == null) cond.await();
                this.out.println(line);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            this.lock.unlock();
        }
    }
}
