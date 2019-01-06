import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 *
 */
public class Cloud {

    private Map<String,User> users;
    private Map<String,Slot> slotsAvailable;
    private Map<String,Slot> slotsOccupied;
    private Map<String,Message> messages;
    private Map<String,Auction> auctions;
    private Lock usersLock;
    private Lock slotsAvLock;
    private Lock slotsOcLock;
    private Lock auctionsLock;
    private Lock messagesLock;

    public Cloud(){
        this.users = new HashMap<>();
        this.slotsAvailable = new HashMap<>();
        this.slotsOccupied = new HashMap<>();
        this.auctions = new HashMap<>();
        this.auctions.put("micro",new Auction("micro"));
        this.auctions.put("medium",new Auction("medium"));
        this.auctions.put("large",new Auction("large"));
        this.messages = new HashMap<>();
        this.usersLock = new ReentrantLock();
        this.slotsAvLock = new ReentrantLock();
        this.slotsOcLock = new ReentrantLock();
        this.auctionsLock = new ReentrantLock();
        this.messagesLock = new ReentrantLock();
    }

    /**Put slot on Map
     *
     * */
    public void setSlot(Slot s) {
        this.slotsAvailable.put(s.getSlotId(),s);
    }

    /**Sign In
     * @return user just created
     * */
    public User signIn(String username, String password, Message msg) throws Exception {
        User u;
        this.usersLock.lock();
        try {
            if (this.users.containsKey(username)) throw new Exception("Username already exists");
            else {
                u = new User(username,password);
                users.put(u.getUsername(),u);
            }

        } finally {
            this.usersLock.unlock();
        }

        this.messagesLock.lock();
        try {
            this.messages.put(username,msg);
        } finally {
            this.messagesLock.unlock();
        }
        return u;
    }

    /**Log In
     * @return user who just logged in
     * */
    public User logIn(String username, String password, Message msg) throws Exception {
        this.usersLock.lock();
        try {
            if(users.containsKey(username)) {
                if(!users.get(username).getPassword().equals(password)) throw new Exception("Wrong Password");
            }
            else throw new Exception("User does not exist");
        } finally {
            this.usersLock.unlock();
        }

        this.messagesLock.lock();
        try {
            if(this.messages.containsKey(username)){
                Message m = this.messages.get(username);
                String line;
                while((line = m.getMessage()) != null){
                    msg.setMessage(line);
                }
                this.messages.put(username,m);
            }
        } finally {
            this.messagesLock.unlock();
        }
        this.usersLock.lock();
        try {
            return this.users.get(username);
        } finally {
            this.usersLock.unlock();
        }

    }

    /** Check user reserved slots
     * @return string with user's slots
     * */
    public String checkSlots(User user) throws Exception{
        this.usersLock.lock();
        String list="";
        try {
            for(String s : this.users.get(user.getUsername()).getUserSlots())
                list += s +" ";
            if (list.equals("")) throw new Exception("There are no reserved slots");
        } finally {
            this.usersLock.unlock();
        }
        return list;
    }

    /** Check user debt
     * @return user debt
     * */
    public double checkDebt(User user) {
        return user.getDebt();
    }

    /** Reserve slot by type
     * @return slot id reserved
     * */
    public String reserveSlot(User user, String type) throws Exception {
        String id = "";
        Slot res;
        this.slotsAvLock.lock();
        this.usersLock.lock();
        this.slotsOcLock.lock();
        try{
            if(existSlot(type)){
                for(Slot s : slotsAvailable.values()) {
                    if (s.getType().equals(type)) {
                        res=s;
                        slotsAvailable.remove(res.getSlotId());
                        id = res.getSlotId();
                        res.startTime();
                        this.users.get(user.getUsername()).setSlot(id);
                        slotsOccupied.put(id,res);
                        break;
                    }
                }

            }
            else{
                throw new Exception("Slot type not available");
            }
        }finally {
            this.usersLock.unlock();
            this.slotsAvLock.unlock();
            this.slotsOcLock.unlock();
        }
        return id;
    }



    /** Release slot by id
     * @return slot id that was released
     * */
    public void releaseSlot(User user, String id) throws Exception {
        Slot s;
        Auction a;
        double slotTime = 0;

        this.slotsOcLock.lock();
        this.slotsAvLock.lock();
        this.usersLock.lock();
        try {

            if (slotsOccupied.containsKey(id)) {
                s = slotsOccupied.get(id);
                slotTime = s.getFinalTime()/1000;
                this.users.get(user.getUsername()).removeSlot(id);
                this.users.get(user.getUsername()).addDebt( slotTime * s.getPrice());

                Slot slot = new Slot(id,s.getType());
                slotsOccupied.remove(id);
                slotsAvailable.put(id,slot);
                //notifyAll();
            }
            else{
                throw new Exception("Slot is not reserved");
            }
        } finally {
            this.usersLock.unlock();
            this.slotsAvLock.unlock();
            this.slotsOcLock.unlock();
        }
    }

    /** Make a bid on an auction by type
     * @return slot id that was bidded
     * */
    public void makeBid (String user, String type, double bid) throws Exception {

        Auction res = null;
        this.auctionsLock.lock();
        this.slotsOcLock.lock();
        try{
            if (existAuction(type)){
                    res = this.auctions.get(type);
                    if (bid>res.getInitialPrice() && bid>res.getMaxPrice()) {
                        res.setMaxPrice(bid);
                        res.setMaxUser(user);
                    } else{
                        throw  new Exception("Your bid is too low");
                    }
                }
            else{
                throw new Exception("No auctions available");
            }
        }finally {
            this.auctionsLock.unlock();
            this.slotsOcLock.unlock();
        }
    }

    /** Create an auction
     * @return slot id of the auction
     * */
    public boolean manageAuction(String type){
        Auction auction;
        String id = null;
        boolean created = false;

        this.slotsOcLock.lock();
        this.auctionsLock.lock();
        this.slotsAvLock.lock();
        try{
                if (!existAuction(type)){
                        for(Slot s : slotsAvailable.values()){
                                if(s.getType().equals(type)){
                                    created = true;
                                    Slot slot = new Slot(s.getSlotId(),s.getType(),s.getPrice());
                                    id = s.getSlotId();
                                    this.slotsAvailable.remove(s.getSlotId());
                                    auction = this.auctions.get(type);
                                    auction.setState(true);
                                    slot.setAuction(true);
                                    this.slotsOccupied.put(id,slot);
                                    break;
                                }
                        }
                        //if (!created) wait();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                this.slotsOcLock.unlock();
                this.auctionsLock.unlock();
                this.slotsAvLock.unlock();
            }
        return created;
    }


    /** End an auction
     *
     * */
    public void endAuction(String type){
        Auction auction = null;
        Slot slot = null;
        User user = null;
        this.auctionsLock.lock();
        this.usersLock.lock();
        this.slotsAvLock.lock();
        this.slotsOcLock.lock();
        try{
            if(existAuction(type)){

                auction = auctions.get(type);

                slot = this.slotsOccupied.get(getAuction(type));
                slot.setAuction(false);

                if(auction.getMaxUser() != null){
                    slot.setFromAuction(true);
                    slot.startTime();
                    slot.setPrice(auction.getMaxPrice());
                    user = this.users.get(auction.getMaxUser());
                    user.setSlot(slot.getSlotId());
                }else{
                    String id = slot.getSlotId();
                    this.slotsOccupied.remove(id);
                    this.slotsAvailable.put(id,new Slot(id,type));
                }
                auction.restartSlot();
            }

        }catch (Exception e ) {
            e.printStackTrace();
        }finally {
            this.auctionsLock.unlock();
            this.usersLock.unlock();
            this.slotsAvLock.unlock();
            this.slotsOcLock.unlock();
        }
    }

    /** Method that checks if there are auctions of type
     * @return boolean
     * */
    public boolean existAuction(String type){
        for(Slot s : this.slotsOccupied.values()){
            if(s.getType().equals(type) && s.isAuction()){
                return true;
            }
        }
        return false;
    }

    /** Method that checks if there are slots available of type
     * @return slot id that was released
     * */
    public boolean existSlot(String type){
        for(Slot s : this.slotsAvailable.values()){
            if(s.getType().equals(type)){
                return true;
            }
        }
        return false;
    }

    public String getAuction(String type){
        for(Slot s : this.slotsOccupied.values()){
            if (s.getType().equals(type) && s.isAuction()){
                return s.getSlotId();
            }
        }
        return null;
    }
}

