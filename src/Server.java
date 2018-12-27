import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Server {

    private static int port = 12345;

    public static void main (String args[]) throws IOException {
        ServerSocket server = new ServerSocket(port);
        Socket socket;
        ReentrantLock lock = new ReentrantLock();
        Cloud cloud = new Cloud();

        //Povoamento das slots 10 por cada tipo (micro,med,large)
        for (int i=0; i<10;i++){
            Slot s = new Slot("s"+i+".micro","micro",0.5);
            cloud.setSlot(s);
        }

        for (int i=0; i<10;i++){
            Slot s = new Slot("s"+i+".med","med",1.0);
            cloud.setSlot(s);
        }

        for (int i=0; i<10;i++){
            Slot s = new Slot("s"+i+".large","large",1.5);
            cloud.setSlot(s);
        }

        try {
            while ((socket = server.accept()) != null) {
                Condition cond = lock.newCondition();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                Message msg = new Message(lock,cond);

                ServerIn serverIn = new ServerIn(msg,in,cloud);
                ServerOut serverOut = new ServerOut(msg,out);

                serverIn.start();
                serverOut.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
