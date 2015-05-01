import java.util.*;
import java.util.concurrent.*;

public class Follower extends Thread{

	Hashtable<String, String> danceCard;
	String type;
	
	protected BlockingQueue queue = null;
	public Follower(BlockingQueue queue, Hashtable<String, String> card, String t) {
        this.queue = queue;
        this.danceCard = card;
        this.type = t;
    }
    
    public void run() {
        try {
        	String s = String.valueOf(getId());
        	queue.put(s);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
}
