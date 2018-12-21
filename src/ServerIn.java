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
        this.user = null;
        this.msg = msg;
        this.in = in;
        this.cloud = cloud;

    }

    public void run() {
        String systemIn, username, password, slotType, slotId;
        try {
            while ((systemIn = in.readLine()) != null) {
                if(systemIn.equals("login")) {
                    username = in.readLine();
                    password = in.readLine();
                    try {
                        this.user = cloud.logIn(username, password, msg);
                        msg.setMessage("Logged In");
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if (systemIn.equals("signin")) {
                    username = in.readLine();
                    password = in.readLine();
                    try {
                        this.user = cloud.signIn(username, password, msg);
                        msg.setMessage("Signed In");
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if (systemIn.equals("checkSlots")) {
                    try {
                        cloud.checkSlots(user);
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if (systemIn.equals("checkDebt")) {
                    try {
                        double debt = cloud.checkDebt(user);
                        msg.setMessage("Debt: " + debt);
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if (systemIn.equals("reserveSlot")) {
                    slotType = in.readLine();
                    try {
                        cloud.reserveSlot(user, slotType, msg);
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if (systemIn.equals("releaseSlot")) {
                    slotId = in.readLine();
                    try {
                        cloud.releaseSlot(user, slotId, msg);
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
