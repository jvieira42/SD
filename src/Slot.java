/**
 *
 */
public class Slot {

    private String id;
    private String type;
    private Double price;
    private String currentUserId;

    public Slot(String id, String type, Double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.currentUserId = "";
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

    public String getCurrentUserId() {
        return this.currentUserId;
    }

    //Setters
    public void setCurrentUser(String userId) {
        this.currentUserId = userId;
    }
}
