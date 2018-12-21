import java.io.BufferedReader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ClientIn extends Thread {

    private BufferedReader in;
    private Menu menu;
    private ReentrantLock lock;
    private Condition cond;

    public ClientIn(BufferedReader in, Menu menu, ReentrantLock lock, Condition cond) {
        this.in = in;
        this.menu = menu;
        this.lock = lock;
        this.cond = cond;
    }

    @Override
    public void run() {
        String serverIn;
        try {
            while((serverIn = in.readLine()) != null) {

                if(serverIn.equals("Logged In") || serverIn.equals("Signed In")) {
                    menu.setOption(2);
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                else if (serverIn.equals("Logged Out") || serverIn.equals("User doesn't exist") || serverIn.equals("Wrong Password")) {
                    menu.setOption(1);
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                System.out.println("\n"+serverIn+"\n");





            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
