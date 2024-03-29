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
                        String list = cloud.checkSlots(this.user);
                        msg.setMessage("Current Slots:\n" + list);
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
                else if(line.equals("reserveSlot")) {
                    String type = in.readLine();
                    int dig = Integer.parseInt(type);
                    switch (dig) {
                        case 1:
                            type = "micro";
                            break;
                        case 2:
                            type = "medium";
                            break;
                        case 3:
                            type = "large";
                            break;
                        default:
                            type = null;
                            break;
                    }
                    try {
                        if(type == null) msg.setMessage("Invalid Option");
                        else {
                            String id = cloud.reserveSlot(user,type);
                            msg.setMessage(id);
                            msg.setMessage("Slot Reserved");
                        }
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if(line.equals("releaseSlot")) {
                    String id = in.readLine();
                    try {
                        cloud.releaseSlot(user,id);
                        msg.setMessage("Slot Released");
                    } catch (Exception e) {
                        msg.setMessage(e.getMessage());
                    }
                }
                else if (line.equals("makeBid")) {
                    String type = in.readLine();
                    String bid = in.readLine();
                    double value = Double.parseDouble(bid);
                    int dig = Integer.parseInt(type);

                    switch (dig) {
                        case 1:
                            type = "micro";
                            break;
                        case 2:
                            type = "medium";
                            break;
                        case 3:
                            type = "large";
                            break;
                        default:
                            type = null;
                            break;
                    }
                    try {
                        if(type == null) msg.setMessage("Invalid Option");
                        else {
                            cloud.makeBid(user.getUsername(),type,value);
                            msg.setMessage("Bid Placed");
                        }
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
