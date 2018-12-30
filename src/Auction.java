/**
 * @author joao
 */
public class Auction{

    private String slot;
    private String type;
    private double initialPrice;
    private double maxPrice;
    private String maxUser;

    public Auction () {
        this.slot = null;
        this.type = null;
        this.initialPrice = 0.0;
        this.maxPrice = 0.0;
        this.maxUser = null;
    }

    public Auction (String slot, String type, double initialPrice, double maxPrice, String maxUser) {
        this.slot = slot;
        this.type = type;
        this.initialPrice = initialPrice;
        this.maxPrice = maxPrice;
        this.maxUser = maxUser;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
