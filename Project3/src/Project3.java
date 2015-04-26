
public class Project3{
	public static void main(String[] args){
		int numLeaders = Integer.parseInt(args[0]);
		int numFollowers = Integer.parseInt(args[1]);
		
		String[] dances = new String [8];
		dances[0] = "Waltz";
		dances[1] = "Tango";
		dances[2] = "Foxtrot";
		dances[3] = "Quickstep";
		dances[4] = "Rumba";
		dances[5] = "Samba";
		dances[6] = "Cha Cha";
		dances[7] = "Jive";
		
		System.out.printf("Leaders: %d	Followers: %d\n", numLeaders, numFollowers);
		for (int i = 0; i < numLeaders; i++){
			(new DanceThread()).start();
		}
	}
}
