/**
 *
 */
public class Slot {

    private String id;
    private String type;
    private double price;


    public Slot(String id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
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

}
