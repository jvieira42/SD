import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Message {

    private String msg;

    public Message() {
        this.msg = null;
    }

    synchronized public String getMessage() throws InterruptedException {
        while(msg == null)
            wait();

        String res = msg;
        msg = null;
        return res;
    }

    synchronized public void setMessage(String msg) {
        this.msg=msg;
        notifyAll();
    }

    /*
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
    */
}
