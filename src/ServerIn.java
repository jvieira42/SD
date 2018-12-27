import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ServerIn extends Thread {

    private Socket socket;
    private Cloud cloud;
    private PrintWriter out;
    private User user;
    private BufferedReader in;
    private ReentrantLock lock;
    private Condition cond;
    private Message msg;


    public ServerIn(BufferedReader in, Socket socket, Cloud cloud, ReentrantLock lock, Condition cond, PrintWriter out, Message msg) {
        this.socket = socket;
        this.cloud = cloud;
        this.out = out;
        this.in = in;
        this.lock = lock;
        this.cond = cond;
        this.msg = msg;
        this.user = new User();
    }

    public void loginUser() {
        try {
            String username = in.readLine();
            String password = in.readLine();
            user.setUsername(username);
            user.setPassword(password);
            user.setLogged(cloud.logIn(username,password));

            if(user.getLogged()) {
                System.out.println("Login successful");
                out.println("login");
            }
            else {
                System.out.println("Login error");
                out.println("Error");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void signInUser() {
        try {
            boolean res;
            String username = in.readLine();
            String password = in.readLine();
            user.setUsername(username);
            user.setPassword(password);
            res = cloud.signIn(username,password);
            if(res) {
                System.out.println("User registered with sucess");
                out.println("signIn");
            }
            else {
                System.out.println("User already registered");
                out.println("User already registered");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void logoutUser() {
        try {
            cloud.logOut(user.getUsername());
            System.out.println("Logout sucessful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String line;
            while((line = in.readLine()) != null) {
                msg.setMessage(line);
                if(line.equals("login")) {
                    loginUser();
                    out.println("login");
                }
                else if(line.equals("signIn")) {
                    signInUser();
                    out.println("signIn");
                }
                else if(line.equals("logout")) {
                    logoutUser();
                    out.println("logout");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
