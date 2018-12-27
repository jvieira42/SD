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
        Socket socket;
        Cloud cloud = new Cloud();
        ReentrantLock lock = new ReentrantLock();

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
            ServerSocket svSocket = new ServerSocket(port);

            while ((socket = svSocket.accept()) != null) {
                Message msg = new Message();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                Condition cond = lock.newCondition();
                System.out.println("Connected to server\n");

                ServerIn serverIn = new ServerIn(in, socket, cloud, lock, cond, out, msg);
                ServerOut serverOut = new ServerOut(in, lock, cond, out, msg);

                serverIn.start();
                serverOut.start();
            }
            svSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
