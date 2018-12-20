import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Message {

    private List<String> listMsg;
    private int i;
    private ReentrantLock lock;
    private Condition cond;

    public Message(ReentrantLock lock, Condition cond) {
        this.listMsg = new ArrayList<>();
        this.i = 0;
        this.lock = lock;
        this.cond = cond;
    }

    public String getMessage() {
        this.lock.lock();
        try {
            if(i!=listMsg.size())
                return this.listMsg.get((i++));
            else return null;
        } finally {
            this.lock.unlock();
        }
    }

    public void setMessage(String msg) {
        this.lock.lock();
        try {
            this.listMsg.add(msg);
            cond.signal();
        } finally {
            this.lock.unlock();
        }
    }


    public ReentrantLock getLock() {
        return this.lock;
    }

    public Condition getCondition() {
        return this.cond;
    }
}
