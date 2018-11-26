import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 */
public class Client {

    public static void main (String args[]) {
        try {
            Socket socket = new Socket("localhost",12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));





            in.close();
            System.out.println("Closing client connection\n");
            socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
