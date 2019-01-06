import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private double debt;
    private List<String> slotsReserved;

    public User() {
        this.username = null;
        this.password = null;
        this.debt = 0.0;
        this.slotsReserved = new ArrayList<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.debt = 0.0;
        this.slotsReserved = new ArrayList<>();
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

    public List<String> getUserSlots() {
        return this.slotsReserved;
    }

    //Setters
    public void setSlot (String s) {
        slotsReserved.add(s);
    }

    public void setUsername (String username) { this.username = username; }

    public void setPassword (String password) { this.password = password; }

    public void addDebt (double debt){
        this.debt += debt;
    }

    public void removeSlot (String id) {
        slotsReserved.remove(id);
    }
}
