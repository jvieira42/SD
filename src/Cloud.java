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


    /**Sign in
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
    public User logIn(String username, String password, Message msg) throws Exception{
        this.usersLock.lock();
        try {
            if(users.containsKey(username)) {
                if(!users.get(username).getPassword().equals(password)) throw new Exception("Wrong Password");
            }
            else throw new Exception("User doesn't exist");
        } finally {
            this.usersLock.unlock();
        }

        this.messagesLock.lock();
        try {
            if(this.messages.containsKey(username)){
                Message m = this.messages.get(username);
                String line;
                while((line = m.getMessage()) != null){
                    m.setMessage(line);
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



}
