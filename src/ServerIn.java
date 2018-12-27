import java.io.BufferedReader;
import java.io.IOException;


/**
 *
 */
public class ServerIn extends Thread {

    private User user;
    private Message msg;
    private BufferedReader in;
    private Cloud cloud;


    public ServerIn(Message msg, BufferedReader in, Cloud cloud) throws IOException {
        this.msg = msg;
        this.in = in;
        this.cloud = cloud;
        this.user = null;
    }

    @Override
    public void run() {
        try {
            String line;
            while((line = in.readLine()) != null) {
                if(line.equals("login")) { //LOGIN
                    String username,password;
                    username = in.readLine();
                    password = in.readLine();
                    try {
                        this.user = cloud.logIn(username, password, msg);
                        msg.setMessage("Logged In");
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if(line.equals("signin")) { //SIGN-IN
                    String user, pass;
                    user = in.readLine();
                    pass = in.readLine();
                    try {
                        this.user = cloud.signIn(user,pass,msg);
                        msg.setMessage("Signed In");
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if(line.equals("exit")) {
                    msg.setMessage("Exit");
                }
                else if(line.equals("logout")) {
                    msg.setMessage("Logged Out");
                }
                else if(line.equals("checkSlots")) {
                    try {
                        cloud.checkSlots(this.user);
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if(line.equals("checkDebt")) {
                    try {
                        double debt = cloud.checkDebt(user);
                        msg.setMessage("Debt: "+debt);
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
