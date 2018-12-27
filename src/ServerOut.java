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
        this.cond = msg.getCondition();
        this.lock = msg.getLock();
    }

    @Override
    public void run() {
        this.lock.lock();
        try {
            String line;
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
