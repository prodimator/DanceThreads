import java.util.*;


public class DanceThread extends Thread {
	Hashtable<String, Long> danceCard;
	String type;
	public DanceThread(Hashtable<String, Long> card, String t){
		this.danceCard = card;
		this.type = t;
	}

	public synchronized void run(){
		System.out.println(this.type);
		
		
	}
	
}
