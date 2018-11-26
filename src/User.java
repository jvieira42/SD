
public class User {

    private String username;
    private String password;
    private double saldo;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.saldo = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
