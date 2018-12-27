import java.io.BufferedReader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ClientOut extends Thread {

    private BufferedReader in;
    private Menu menu;
    private ReentrantLock lock;
    private Condition cond;

    public ClientOut(BufferedReader in, Menu menu, ReentrantLock lock, Condition cond) {
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
                if(line.equals("login")){
                    menu.setOption(1);
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                else if(line.equals("signIn")) {
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                else if(line.equals("checkSlots")) {
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                else if(line.equals("checkDebt")) {
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                else if(line.equals("reserveSlot")) {
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                else if(line.equals("releaseSlot")) {
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                else if(line.equals("Error") || line.equals("User already registered")) {
                    menu.setOption(0);
                    this.lock.lock();
                    cond.signal();
                    this.lock.unlock();
                }
                else if(line.equals("quit")) {
                    System.exit(1);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
