/**
 *
 */
public class Slot {

    private String id;
    private String type;
    private Double price;


    public Slot(String id, String type, Double price) {
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

    public Double getPrice() {
        return this.price;
    }

}
