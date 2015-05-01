import java.util.*;
import java.util.concurrent.*;

public class Leader extends Thread {

	Hashtable<String, String> danceCard;
	String type;
	
	protected BlockingQueue queue = null;
    public Leader(BlockingQueue queue, Hashtable<String, String> card, String t) {
        this.queue = queue;
        this.danceCard = card;
        this.type = t;
    }
    
    
    public void run() {
    	try {
    		doStuff();
    		printTable();
        } catch (InterruptedException e) {
             e.printStackTrace();
        }
    }
    
    
    
    public synchronized void doStuff() throws InterruptedException{
    	String id = (String) queue.take();
    	if (danceCard.containsValue(id) == false){
    		danceCard.put("Waltz", id);
    	}
    	
    }
    
    
	public void printTable(){
		//System.out.println("This is leader "+getId()+" table");
		if (danceCard.get("Waltz")==""){ danceCard.put("Waltz", "------");
		}
		System.out.println("Waltz		with "+ danceCard.get("Waltz"));
		
		if (danceCard.get("Tango")==""){ danceCard.put("Tango", "------");
		}
		System.out.println("Tango		with "+ danceCard.get("Tango"));
		
		if (danceCard.get("Foxtrot")==""){ danceCard.put("Foxtrot", "------");
		}
		System.out.println("Foxtrot		with "+ danceCard.get("Foxtrot"));
		
		
		if (danceCard.get("Quickstep")==""){ danceCard.put("Quickstep", "------");
		}
		System.out.println("Quickstep	with "+ danceCard.get("Quickstep"));
		
		
		if (danceCard.get("Rumba")==""){ danceCard.put("Rumba", "------");
		}
		System.out.println("Rumba		with "+ danceCard.get("Rumba"));
		
		
		if (danceCard.get("Scamba")==""){ danceCard.put("Scamba", "------");
		}
		System.out.println("Scamba		with "+ danceCard.get("Scamba"));
		
		if (danceCard.get("Cha Cha")==""){ danceCard.put("Cha Cha", "------");
		}
		System.out.println("Cha Cha		with "+ danceCard.get("Cha Cha"));
		
		if (danceCard.get("Jive")==""){ danceCard.put("Jive", "------");
		}
		System.out.println("Jive		with "+ danceCard.get("Jive"));	
		System.out.println("\n");
	}

}


