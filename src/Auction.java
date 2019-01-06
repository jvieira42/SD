/**
 * @author joao
 */
public class Auction{



    private String type;
    private boolean state;
    private double initialPrice;
    private double maxPrice;
    private String maxUser;

    public Auction (String type) {
        this.type = type;
        this.state = false;
        switch (this.getType()) {
            case "micro":
                this.initialPrice = 0.01;
                break;
            case "medium":
                this.initialPrice = 0.5;
                break;
            case "large":
                this.initialPrice = 1.0;
                break;
        }
        this.maxPrice = this.initialPrice;
        this.maxUser = null;
    }

    public void restartSlot(){
        this.setState(false);
        switch (this.getType()) {
            case "micro":
                this.setInitialPrice(0.01);
                break;
            case "medium":
                this.setInitialPrice(0.5);
                break;
            case "large":
                this.setInitialPrice(1.0);
                break;
        }
        this.setMaxPrice(this.getInitialPrice());
        this.maxUser = null ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(String maxUser) {
        this.maxUser = maxUser;
    }
}
