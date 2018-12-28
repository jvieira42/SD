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
    private Lock usersLock;
    private Lock slotsAvLock;
    private Lock slotsOcLock;
    private Lock messagesLock;

    public Cloud(){
        this.users = new HashMap<>();
        this.slotsAvailable = new HashMap<>();
        this.slotsOccupied = new HashMap<>();
        this.messages = new HashMap<>();
        this.usersLock = new ReentrantLock();
        this.slotsAvLock = new ReentrantLock();
        this.slotsOcLock = new ReentrantLock();
        this.messagesLock = new ReentrantLock();
    }

    /**Create slot
     * @return new slot
     * */
    public Slot createSlot(String id, String type, double price) {
        return new Slot(id,type,price);
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
            for(String s : this.users.get(user.getUsername()).getUserSlots().keySet())
                list = list + s +"\n";
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
        Slot res = null;
        this.slotsAvLock.lock();
        try {
            for(Slot s : slotsAvailable.values()) {
                if (s.getType().equals(type)) {
                    res=s;
                    id = s.getSlotId();
                    this.usersLock.lock();
                    this.users.get(user.getUsername()).setSlot(s);
                    this.usersLock.unlock();
                    slotsAvailable.remove(s.getSlotId());
                    break;
                }
                else throw new Exception("Slot type not available");
            }
        } finally {
            this.slotsAvLock.unlock();
        }
        this.slotsOcLock.lock();
        try {
            slotsOccupied.put(res.getSlotId(),res);
        } finally {
            this.slotsOcLock.unlock();
        }
        return id;
    }

    /** Release slot by id
     * @return slot id that was released
     * */
    public String releaseSlot(User user, String id) throws Exception {
        Slot s;
        this.slotsOcLock.lock();
        try {
            if (slotsOccupied.containsKey(id)) {
                s = slotsOccupied.get(id);
                slotsOccupied.remove(id);
                this.usersLock.lock();
                this.users.get(user.getUsername()).removeSlot(id);
                this.usersLock.unlock();
                this.slotsAvLock.lock();
                slotsAvailable.put(s.getSlotId(),s);
                this.slotsAvLock.unlock();
            }
            else throw new Exception("Slot is not reserved");
        } finally {
            this.slotsOcLock.unlock();
        }
        return id;
    }

}
