public class AuctionHouse extends Thread {

    private Cloud cloud;
    private String type;

    public AuctionHouse(Cloud cloud, String type) {
        this.cloud = cloud;
        this.type = type;
    }

    @Override
    public void run() {
        String auctionId;
        while(true){

            try {
                auctionId = cloud.manageAuction(type);
                sleep(3000);
                cloud.endAuction(auctionId);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
