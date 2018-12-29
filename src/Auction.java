/**
 * @author joao
 */
public class Auction extends Thread { //INACABADO

    private Slot slot;
    private double initialPrice;
    private double maxPrice;
    private User maxUser;

    public Auction () {
        this.slot = null;
        this.initialPrice = 0.0;
        this.maxPrice = 0.0;
        this.maxUser = null;
    }

    public Auction (Slot slot, double initialPrice, double maxPrice, User maxUser) {
        this.slot = slot;
        this.initialPrice = initialPrice;
        this.maxPrice = maxPrice;
        this.maxUser = maxUser;
    }

    @Override
    public void run() {

    }
}
