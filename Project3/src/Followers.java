import java.util.Hashtable;


public class Followers extends Thread {
	public int counter = 0;
	Hashtable<String,String> mailbox = new Hashtable<String, String>();

	public void run(){
		synchronized(this){
			try {
				System.out.println("Starting follower...");
				while(Project3.numLeadersLooking == 0){
					wait();
				}
				
	        } catch (InterruptedException e) {}
		}
    }
	
	
	
	public synchronized void doStuff() throws InterruptedException{
		// while (time<500000000000) {
         //	wait();
         //}
		// notify();
		//String dance = leader.getRequest();
		//if (mailbox.get(dance) == ""){
			//mailbox.put(dance, "1");
		//}
		//printFollowTable();
		
		
		
	}
	public void printFollowTable(){
		System.out.println("This is follower "+getId()+" table");
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
	public void createFollowTable(){
		mailbox.put("Waltz", "");
		mailbox.put("Tango", "");
		mailbox.put("Foxtrot", "");
		mailbox.put("Quickstep", "");
		mailbox.put("Rumba", "");
		mailbox.put("Scamba", "");
		mailbox.put("Cha Cha", "");
		mailbox.put("Jive", "");
	}
	
	
/*
===============
while true {
    partner = null

    under bigAssMutexLock:
        while partner == null:
            waitingForDancer.wait()

            if there's a leader in waitingLeaders whose leader.waitingDance is something I need
                partner = guy in list
                remove guy from list

    print out stuff about me and that guy for the output
}
*/
	
	
}
