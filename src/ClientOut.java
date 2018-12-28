import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ClientOut extends Thread {

    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private Menu menu;
    private ReentrantLock lock;
    private Condition cond;

    public ClientOut(Socket socket, Menu menu, ReentrantLock lock, Condition cond) {
        try {
            this.socket = socket;
            this.menu = menu;
            this.in = new BufferedReader(new InputStreamReader(System.in));
            this.out = new PrintWriter(socket.getOutputStream(),true);
            this.lock = lock;
            this.cond = cond;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String line;
            menu.show();
            while((line = in.readLine()) != null) {

                switch (menu.getOption()) {
                    case 1:
                        if(line.equals("1")) {
                            out.println("login");
                            System.out.println("Username:");
                            line = in.readLine();
                            out.println(line);
                            System.out.println("Password:");
                            line = in.readLine();
                            out.println(line);
                            this.lock.lock();
                            cond.await();
                            this.lock.unlock();
                            line = "1";
                        }
                        else if(line.equals("2")) {
                            out.println("signin");
                            System.out.println("Username:");
                            line = in.readLine();
                            out.println(line);
                            System.out.println("Password:");
                            line = in.readLine();
                            out.println(line);
                            this.lock.lock();
                            cond.await();
                            this.lock.unlock();
                            line = "2";
                        }
                        else if(line.equals("0")){
                            out.println("exit");
                            this.lock.lock();
                            cond.await();
                            this.lock.unlock();
                            line = "0";
                        }
                        else {
                            System.out.println("Invalid option");
                        }

                        if (line.equals("1") || line.equals("2") || line.equals("3") || line.equals("m")){
                            System.out.println("\n");
                            menu.show();
                        }
                        break;

                    case 2:
                        if(line.equals("1")) {
                            out.println("checkSlots");
                        }
                        else if(line.equals("2")) {
                            out.println("checkDebt");
                        }
                        else if(line.equals("3")) {
                            out.println("reserveSlot");
                            System.out.println("Choose a type: micro, medium or large:");
                            line = in.readLine();
                            out.println(line);
                            this.lock.lock();
                            cond.await();
                            this.lock.unlock();
                            line = "3";
                        }
                        else if(line.equals("5")) {
                            out.println("releaseSlot");
                            System.out.println("SlotId to be released:");
                            line = in.readLine();
                            out.println(line);
                            this.lock.lock();
                            cond.await();
                            this.lock.unlock();
                            line = "5";

                        }
                        else if(line.equals("0")) {
                            out.println("logout");
                            this.lock.lock();
                            cond.await();
                            this.lock.unlock();
                            line = "0";
                        }
                        else {
                            System.out.println("Invalid option");
                        }
                        if (line.equals("0") || line.equals("1") || line.equals("2") || line.equals("3") || line.equals("4") || line.equals("5") || line.equals("m")){
                            System.out.println("\n");
                            menu.show();
                        }
                        break;
                }
            }
        socket.shutdownOutput();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
