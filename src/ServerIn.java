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
        String systemIn;
        try {
            while ((systemIn = in.readLine()) != null) {




            }
            in.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
