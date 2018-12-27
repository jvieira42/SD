import java.util.HashMap;
import java.util.Map;

public class User {

    private String username;
    private String password;
    private boolean logged;
    private double debt;
    private Map<String,Slot> slotsReserved;

    public User() {
        this.username = null;
        this.password = null;
        this.debt = 0;
        this.slotsReserved = new HashMap<>();
    }

    public User(String username, String password, boolean logged) {
        this.username = username;
        this.password = password;
        this.logged = logged;
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

    public boolean getLogged() {
        return logged;
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

    public void setUsername (String username) { this.username = username; }

    public void setPassword (String password) { this.password = password; }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void removeSlot (String id) {
        slotsReserved.remove(id);
    }
}
