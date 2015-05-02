import java.util.*;
import java.util.concurrent.*;

public class Project3 {
	public static void main(String[] args) throws Exception{
		Hashtable<String, String> danceCard = new Hashtable<String, String>();
		danceCard.put("Waltz", "");
		danceCard.put("Tango", "");
		danceCard.put("Foxtrot", "");
		danceCard.put("Quickstep", "");
		danceCard.put("Rumba", "");
		danceCard.put("Scamba", "");
		danceCard.put("Cha Cha", "");
		danceCard.put("Jive", "");
		
		
		BlockingQueue queue = new LinkedBlockingQueue();

        Leader leader1 = new Leader(queue, danceCard, "L" );
		Follower follower1 = new Follower(queue, danceCard, "F");
		follower1.run();
		leader1.run();
       
        

        
        
       
	}
}
