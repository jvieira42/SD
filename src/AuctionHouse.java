import java.util.concurrent.locks.ReentrantLock;

public class AuctionHouse extends Thread {

    private Cloud cloud;
    private String type;

    public AuctionHouse(Cloud cloud, String type) {
        this.cloud = cloud;
        this.type = type;
    }

    @Override
    public void run() {
        boolean active;
        while(true){
            try {
                   active = cloud.manageAuction(type);
                    if(active) {
                        sleep(30000);
                        cloud.endAuction(type);
                    } else {

                        sleep(1000);
                    }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
