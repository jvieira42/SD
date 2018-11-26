import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 */
public class ServerIn implements Runnable {

    private BufferedReader input;


    public ServerIn(BufferedReader input) throws IOException {
        this.input = input;

    }

    public void run() {
        try {
            String line;



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
