import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Slot {


    private String id;
    private boolean isAuction;
    private boolean fromAuction;
    private String type;
    private double price;
    private double time;
    private String user;

    public Slot(String id, String type, double price) {
        this.id = id;
        this.isAuction = false;
        this.fromAuction = false;
        this.type = type;
        this.price = price;
        this.time = 0;
        this.user = null;

    }

    public Slot(String id ,String type){
        this.id = id;
        this.isAuction = false;
        this.fromAuction = false;
        this.type = type;
        switch (type){
            case "micro":
                this.price = 0.5;
                break;
            case "medium":
                this.price = 1.0;
                break;
            case "large":
                this.price = 1.5;
                break;
        }
        this.time = 0;
        this.user = null;
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

    public void setPrice(double price) {
        this.price = price;
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

    public boolean isAuction() {
        return isAuction;
    }

    public void setAuction(boolean auction) {
        isAuction = auction;
    }

    public boolean isFromAuction() {
        return fromAuction;
    }

    public void setFromAuction(boolean fromAuction) {
        this.fromAuction = fromAuction;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
