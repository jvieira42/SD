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
public class ClientIn extends Thread {
    private Socket socket;
    private PrintWriter out;
    private Menu menu;
    private BufferedReader in;
    private ReentrantLock lock;
    private Condition cond;

    public ClientIn(Socket socket, ReentrantLock lock, Condition cond, Menu menu) {
        try {
            this.socket = socket;
            this.lock = lock;
            this.cond = cond;
            this.menu = menu;
            this.out = new PrintWriter(socket.getOutputStream(),true);
            this.in = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            menu.Manager();
            String line;
            while((line = in.readLine()) != null) {
                if(menu.getOption() == 0) { //startMenu
                    if(line.equals("1")) { //Login
                        out.println("login");
                        System.out.println("Username: ");
                        line = in.readLine();
                        out.println(line);
                        System.out.println("Password: ");
                        line = in.readLine();
                        out.println(line);

                        this.lock.lock();
                        cond.await();
                        this.lock.unlock();
                        clear();
                        menu.Manager();
                    }
                    else if(line.equals("2")) { //SignIn
                        out.println("signIn");
                        System.out.println("Username: ");
                        line = in.readLine();
                        out.println(line);
                        System.out.println("Password: ");
                        line = in.readLine();
                        out.println(line);

                        this.lock.lock();
                        cond.await();
                        this.lock.unlock();
                        clear();
                        menu.Manager();
                    }
                    else if(line.equals("0")) { //Quit
                        out.println("quit");
                        break;
                    }
                    else {
                        System.out.println("Invalid Option");
                    }
                }
                else if(menu.getOption() == 1){ //loginMenu
                    if(line.equals("1")) { //CurrentSlots
                        out.println("checkSlots");

                        this.lock.lock();
                        cond.await();
                        this.lock.unlock();
                        clear();
                        menu.Manager();
                    }
                    else if(line.equals("2")) { //CurrentDebt
                        out.println("checkDebt");

                        this.lock.lock();
                        cond.await();
                        this.lock.unlock();
                        clear();
                        menu.Manager();
                    }
                    else if(line.equals("3")) { //ReserveSlot
                        out.println("reserveSlot");
                        System.out.println("Slot type (micro,med,large):");
                        line = in.readLine();
                        out.println(line);

                        this.lock.lock();
                        cond.await();
                        this.lock.unlock();
                        clear();
                        menu.Manager();
                    }
                    else if(line.equals("4")) { //ReleaseSlot
                        out.println("releaseSlot");
                        System.out.println("Id to be released: ");
                        line = in.readLine();
                        out.println(line);

                        this.lock.lock();
                        cond.await();
                        this.lock.unlock();
                        clear();
                        menu.Manager();
                    }
                    else if(line.equals("0")) { //Quit
                        out.println("quit");
                        break;
                    }
                    else {
                        System.out.println("Invalid Option");
                    }
                }


                /*
                 if(serverIn.equals("SlotReserved") || serverIn.equals("Logged In") || serverIn.equals("Signed In")) {
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
                */




            }
            socket.shutdownOutput();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void clear(){
        for(int i=0;i<50;i++){
            System.out.println();
        }
    }

}
