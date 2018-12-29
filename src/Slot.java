import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Slot {

    private String id;
    private String type;
    private double price;
    private double time;
    private Lock lock;


    public Slot(String id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.time = 0;
        this.lock = new ReentrantLock();
    }

    //Getters
    public String getSlotId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public double getPrice() {
        return this.price;
    }

    public Lock getLock(){
        return this.lock;
    }

    public void startTime(){
        this.time = System.currentTimeMillis();
    }

    public double getTime(){
        return this.time;
    }

    public void resetTime(){
        this.time = 0;
    }

    public double getFinalTime(){
        return System.currentTimeMillis() - this.time;
    }
}
