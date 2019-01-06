import java.io.BufferedReader;
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

    public static void main (String args[]) throws Exception {
        ServerSocket server = new ServerSocket(port);
        Socket socket;
        ReentrantLock lock = new ReentrantLock();
        Cloud cloud = new Cloud();
        AuctionHouse micro = new AuctionHouse(cloud,"micro");
        AuctionHouse medium = new AuctionHouse(cloud,"medium");
        AuctionHouse large = new AuctionHouse(cloud,"large");


        //Povoamento das slots, 10 por cada tipo (micro,med,large)
        for (int i=0; i<5;i++){
            Slot sMicro = new Slot("s"+i+".micro","micro",0.5);
            Slot sMed = new Slot("s"+i+".medium","medium",1.0);
            Slot sLarge = new Slot("s"+i+".large","large",1.5);
            cloud.setSlot(sMicro);
            cloud.setSlot(sMed);
            cloud.setSlot(sLarge);
        }

        try {
            //Leiloes
            micro.start();
            medium.start();
            large.start();

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
