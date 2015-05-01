import java.util.*;
public class Leader extends Thread {
	
	public int numLeadersLooking = 0;
	public Vector dance = new Vector();
	Hashtable<String, String> danceCard;
	
	public Leader(Hashtable<String, String> card){
		this.danceCard = card;
	}
	@Override
	public void run(){
		try{
			while(numLeadersLooking < 1 ){ //number of leaders
				addLeadRequest();
			}
		} catch (InterruptedException e) {}
		
	}
	
	
	public synchronized void addLeadRequest() throws InterruptedException {
		numLeadersLooking++;
		while (dance.size() == 8) {
            wait();
        }
		String s = String.valueOf(getId());
		dance.addElement(s);
		notify();
	}
	
	public synchronized String getID() throws InterruptedException {
		notify();
		
        while (dance.size() == 0) {
            wait();
        }
        
        String message = (String) dance.firstElement();
        dance.removeElement(message);
        return message;
	}
	public int getNumLeaders(){
		return numLeadersLooking;
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
