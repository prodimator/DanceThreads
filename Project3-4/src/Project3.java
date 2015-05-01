import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Follower extends Thread{
	Buffer buff;
	int id;
	int[] stopBuffer;
	Hashtable<Integer, String>[] master;
	Hashtable<Integer, String> danceCard = new Hashtable<Integer, String>();
    public Follower(Buffer buff,int[] stopBuffer , Hashtable<Integer, String>[] master, int i) {
    	this.buff = buff;
    	danceCard.put(1, "");
		danceCard.put(2, "");
		danceCard.put(3, "");
		danceCard.put(4, "");
		danceCard.put(5, "");
		danceCard.put(6, "");
		danceCard.put(7, "");
		danceCard.put(8, "");
    	this.id = i;
    	this.master=master;
    	this.stopBuffer=stopBuffer;
  }
	public void run(){
		while (this.stopBuffer[id-1]!=id){
			buff.put(id);
		}
		//buff.put(id);


		//}
	}
	
	
	
	
	public void printFollowerTable(){
		System.out.println("This is leader "+getId()+" table");
				if (danceCard.get(1)==""){ danceCard.put(1, "------");
				}
				System.out.println("Waltz		with "+ danceCard.get(1));
				
				if (danceCard.get(2)==""){ danceCard.put(2, "------");
				}
				System.out.println("Tango		with "+ danceCard.get(2));
				
				if (danceCard.get(3)==""){ danceCard.put(3, "------");
				}
				System.out.println("Foxtrot		with "+ danceCard.get(3));
				
				
				if (danceCard.get(4)==""){ danceCard.put(4, "------");
				}
				System.out.println("Quickstep	with "+ danceCard.get(4));
				
				
				if (danceCard.get(5)==""){ danceCard.put(5, "------");
				}
				System.out.println("Rumba		with "+ danceCard.get(5));
				
				
				if (danceCard.get(6)==""){ danceCard.put(6, "------");
				}
				System.out.println("Scamba		with "+ danceCard.get(6));
				
				if (danceCard.get(7)==""){ danceCard.put(7, "------");
				}
				System.out.println("Cha Cha		with "+ danceCard.get(7));
				
				if (danceCard.get(8)==""){ danceCard.put(8, "------");
				}
				System.out.println("Jive		with "+ danceCard.get(8));	
				System.out.println("\n");
			}
	
}

class Leader extends Thread{
	Buffer buff;
	int id;
	int[] stopBuffer;
	//Vector <Hashtable<Integer, String>> master;
	Hashtable<Integer, String>[] master;
	Hashtable<Integer, String> danceCard = new Hashtable<Integer, String>();
	int num = 0;
    public Leader(Buffer buff,int[] stopBuffer, Hashtable<Integer, String>[] master, int i) {
    	this.buff = buff;
    	//this.danceCard = danceCard;
		//Hashtable<Integer, String> danceCard = new Hashtable<Integer, String>();
		danceCard.put(1, "");
		danceCard.put(2, "");
		danceCard.put(3, "");
		danceCard.put(4, "");
		danceCard.put(5, "");
		danceCard.put(6, "");
		danceCard.put(7, "");
		danceCard.put(8, "");
    	this.id = i;
    	this.stopBuffer=stopBuffer;
    	this.master = master;
    	this.master[id-1]=danceCard;
    }
	public void run(){

		while (buff.size >0){
			int i = buff.get();
			String s = String.valueOf(i);
			//loop through all entries to see if this dancer already has 2 requests
			//randomly access a spot on hashtable
				//check if it is empty
			int alreadyDoingDanceCount = 0;
			sort(s, alreadyDoingDanceCount);
		}
		
	}
	public synchronized void sort(String s, int alreadyDoingDanceCount){
		
		Random rand = new Random();
		int  n = rand.nextInt(8) + 1;
		int check=0;
		boolean alreadyDoingDance = false;
		int isFull=1; //checks to see if 
		//***need tocheck to see if there is more than 2 of the same dances for a follower
		//this stops the follower -- 	stopBuffer[Integer.parseInt(s)-1] = Integer.parseInt(s);
			//puts the id in a stopBuffer
		
		for (int i = 0; i<this.master.length; i++){
			if (i != (id-1)){
				if (this.master[i].get(n).equals(s)){
					alreadyDoingDance = true;
					alreadyDoingDanceCount++;
				}
			}
		
		}
				
		for (int j = 1; j < 9; j++){
			if (this.master[id-1].get(j).equals(s)){
				check++; //this checks to see if follower is already with this leader 
			}
			if (this.master[id-1].get(j).equals("")==false){
				isFull++; //if this == 8, then leader is full
			}
		}
		if (alreadyDoingDanceCount<8){
			if (isFull<=8 && check < 2 && alreadyDoingDance == false){
				if (this.master[id-1].get(n).equals("")){
					this.master[id-1].put(n, s);
					notifyAll();
				}
				else{
					sort(s, alreadyDoingDanceCount);
				}
			}
			else{
				stopBuffer[Integer.parseInt(s)-1] = Integer.parseInt(s);
			}
		}
		else { stopBuffer[Integer.parseInt(s)-1] = Integer.parseInt(s); }
	}
	
	public void printTable(){
		System.out.println("This is leader "+id+" table");
		if (danceCard.get(1)==""){ danceCard.put(1, "------");
		}
		System.out.println("Waltz		with "+ danceCard.get(1));
		
		if (danceCard.get(2)==""){ danceCard.put(2, "------");
		}
		System.out.println("Tango		with "+ danceCard.get(2));
		
		if (danceCard.get(3)==""){ danceCard.put(3, "------");
		}
		System.out.println("Foxtrot		with "+ danceCard.get(3));
		
		
		if (danceCard.get(4)==""){ danceCard.put(4, "------");
		}
		System.out.println("Quickstep	with "+ danceCard.get(4));
		
		
		if (danceCard.get(5)==""){ danceCard.put(5, "------");
		}
		System.out.println("Rumba		with "+ danceCard.get(5));
		
		
		if (danceCard.get(6)==""){ danceCard.put(6, "------");
		}
		System.out.println("Scamba		with "+ danceCard.get(6));
		
		if (danceCard.get(7)==""){ danceCard.put(7, "------");
		}
		System.out.println("Cha Cha		with "+ danceCard.get(7));
		
		if (danceCard.get(8)==""){ danceCard.put(8, "------");
		}
		System.out.println("Jive		with "+ danceCard.get(8));	
		System.out.println("\n");
	}

	
}


public class Project3 {
	public static void main(String[] args){
		int numLeaders = Integer.parseInt(args[0]);
		int numFollowers = Integer.parseInt(args[1]);
		
		//Vector <Hashtable<Integer, String>> master = new <Hashtable<Integer, String>> Vector(numLeaders);
		Hashtable<Integer, String>[] master = new Hashtable[numLeaders];
		int[] stopBuffer = new int[numFollowers];
		
		
		
		Buffer buff = new Buffer(numFollowers); //number of followers
		for( int i = 1;i<numFollowers+1; i++){
			Follower follower = new Follower(buff, stopBuffer, master, i);
			follower.start();
		}
		
		Leader leader1 = new Leader(buff,stopBuffer, master, 1);
		Leader leader2 = new Leader(buff,stopBuffer, master, 2);
		Leader leader3 = new Leader(buff,stopBuffer, master, 3);


		leader1.start();
		leader2.start();
		leader3.start();
		

		try{
			leader1.join();
			leader2.join();
			leader3.join();

	    }catch(InterruptedException e) {}
	    
		leader1.printTable();
		leader2.printTable();
		leader3.printTable();
		//follower1.printTable();
	    System.out.println("main is all done! ");

	}
}
