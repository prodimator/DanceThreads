import java.util.*;


public class Leaders extends Thread {
	Hashtable<String,String> mailbox = new Hashtable<String, String>();
	//String[] danceCard = {"Waltz", "Tango", "Foxtrot", "Quickstep", "Rumba", "Scamba", "Cha Cha", "Jive"};
	Vector<String> vec = new Vector<String>();
	String danceRequest;
	
	
	//long threadID;
	//int time = 0;
	public void run(){
		synchronized(this){
			try{
				//printTable();
				doStuff();
				System.out.println("Done Leader");
			} catch (InterruptedException e) {}
		}
    }	
	public synchronized void doStuff() throws InterruptedException{
		Project3.numLeadersLooking++;
		createTable();
		notify();
		
		
		
	}
	
	public synchronized String getRequest() throws InterruptedException {
		notify();
		return danceRequest;
		
	}
	
	
	
	public void createTable(){
		mailbox.put("Waltz", "");
		mailbox.put("Tango", "");
		mailbox.put("Foxtrot", "");
		mailbox.put("Quickstep", "");
		mailbox.put("Rumba", "");
		mailbox.put("Scamba", "");
		mailbox.put("Cha Cha", "");
		mailbox.put("Jive", "");
	}
	public void printTable(){
		System.out.println("This is leader "+getId()+" table");
		if (mailbox.get("Waltz")==""){ mailbox.put("Waltz", "------");
		}
		System.out.println("Waltz		with "+ mailbox.get("Waltz"));
		
		if (mailbox.get("Tango")==""){ mailbox.put("Tango", "------");
		}
		System.out.println("Tango		with "+ mailbox.get("Tango"));
		
		if (mailbox.get("Foxtrot")==""){ mailbox.put("Foxtrot", "------");
		}
		System.out.println("Foxtrot		with "+ mailbox.get("Foxtrot"));
		
		
		if (mailbox.get("Quickstep")==""){ mailbox.put("Quickstep", "------");
		}
		System.out.println("Quickstep	with "+ mailbox.get("Quickstep"));
		
		
		if (mailbox.get("Rumba")==""){ mailbox.put("Rumba", "------");
		}
		System.out.println("Rumba		with "+ mailbox.get("Rumba"));
		
		
		if (mailbox.get("Scamba")==""){ mailbox.put("Scamba", "------");
		}
		System.out.println("Scamba		with "+ mailbox.get("Scamba"));
		
		
		if (mailbox.get("Cha Cha")==""){ mailbox.put("Cha Cha", "------");
		}
		System.out.println("Cha Cha		with "+ mailbox.get("Cha Cha"));
		
		if (mailbox.get("Jive")==""){ mailbox.put("Jive", "------");
		}
		System.out.println("Jive		with "+ mailbox.get("Jive"));	
		System.out.println("\n");
	}

	
	
	
	
	
	
	/*
==============
while true {
    under bigAssMutexLock
        pick a dance randomly 

        this.waitingDance = danceX
        waitingLeaders.add(this)

        waitingForDancer.notify

    // dancing stuff?
} 
 */
	
	
}
