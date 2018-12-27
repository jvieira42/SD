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
        try {
            String line;
            while((line = in.readLine()) != null) {

                if(line.equals("Logged In") || line.equals("Signed In")) {
                    menu.setOption(2);
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                else if(line.equals("Logged Out") || line.equals("User does not exist") || line.equals("Wrong Password")) {
                    menu.setOption(1);
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }






                System.out.println("\n"+line+"\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
