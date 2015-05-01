import java.util.*;

public class Project3{
	public static volatile int numLeadersLooking=0;
	public static void main(String[] args){
		int numLeaders = Integer.parseInt(args[0]);
		int numFollowers = Integer.parseInt(args[1]);

		
		System.out.println("Leaders: "+numLeaders+ "	Followers: "+numFollowers);
		System.out.println("");
		Followers follower = new Followers();
		follower.start();
		Leaders leader = new Leaders();
		leader.start();

	}
}
