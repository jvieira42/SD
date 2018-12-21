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
        String systemIn, username, password;
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




            }

            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
