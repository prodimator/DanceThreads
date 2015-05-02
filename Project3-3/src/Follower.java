import java.util.*;

public class Follower extends Thread {

	Hashtable<String, String> danceCard;
	Leader leader;
	 
    Follower(Leader l, Hashtable<String, String> card) {
    	this.danceCard = card;
        leader = l;
    }
 
    @Override
    public void run() {
        try {
           while (leader.getNumLeaders()>0) {
                String message = leader.getID();
                System.out.println("Thread "+message);
                
          }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void compare(String message){
    	
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) {
    	Hashtable<String, String> danceCard = new Hashtable<String, String>();
		danceCard.put("Waltz", "");
		danceCard.put("Tango", "");
		danceCard.put("Foxtrot", "");
		danceCard.put("Quickstep", "");
		danceCard.put("Rumba", "");
		danceCard.put("Scamba", "");
		danceCard.put("Cha Cha", "");
		danceCard.put("Jive", "");
    	
    	
    	Leader leader = new Leader(danceCard);
        leader.start();
        new Follower(leader, danceCard).start();
    }

}
