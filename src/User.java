import java.util.HashMap;
import java.util.Map;

public class User {

    private String username;
    private String password;
    private double debt;
    private Map<String,Slot> slotsReserved;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.debt = 0;
        this.slotsReserved = new HashMap<>();
    }

    //Getters
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public double getDebt() {
        return this.debt;
    }

    public Map<String,Slot> getUserSlots() {
        return this.slotsReserved;
    }

    //Setters
    public void setSlot (Slot s) {
        slotsReserved.put(s.getSlotId(),s);
    }

    public void removeSlot (String id) {
        slotsReserved.remove(id);
    }
}
