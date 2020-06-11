import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Voter voter[];
		Party party[];
		Candidate candidate[];
		PollStation pollStation;
		Scanner scan = new Scanner(new File("party"));
		int length = scan.nextInt();
		scan.nextLine();
		party = new Party[length];
		for(int i=0;i<length;i++)
		{
			String s = scan.nextLine();
			String ss[] = s.split(",");
			party[i]= new Party(ss[0],Integer.parseInt(ss[1]),Integer.parseInt(ss[2]));
		}
		scan = new Scanner(new File("candidate"));
		length = scan.nextInt();
		scan.nextLine();
		candidate = new Candidate[length];
		for(int i=0;i<length;i++)
		{
			String s = scan.nextLine();
			String ss[] = s.split(",");
			Party p = getParty(ss[1],party);
			candidate[i] = new Candidate(i+1,ss[0],p,Integer.parseInt(ss[2]));
			p.register(candidate[i]);
		}
		scan = new Scanner(new File("voter"));
		length = scan.nextInt();
		scan.nextLine();
		voter = new Voter[length];
		for(int i=0;i<length;i++)
		{
			String s = scan.nextLine();
			String ss[] = s.split(",");
			voter[i]= new Voter(Integer.parseInt(ss[0]),getCandidate(ss[1],candidate),getCandidate(ss[2],candidate));
		}
		scan.close();
		System.out.println("Do you want any candidate to change party? Enter Y/N");
		scan = new Scanner(System.in);
		String cp = scan.nextLine();
		while(cp.equals("Y") || cp.equals("y"))
		{
			System.out.println("Enter the candidate name:");
			String s = scan.nextLine();
			Candidate c = getCandidate(s,candidate);
			if(c==null)
			{
				System.out.println("The candidate is not registered");
			}
			else
			{
				System.out.println("Enter the party to be joined:");
				s=scan.nextLine();
				Party p = getParty(s,party);
				if(p!=null)
					c.changeParty(p); 
				else
					System.out.println("The party doesn't exist");
			}
			System.out.println("Do you want any other candidate to change party? Enter Y/N");
			cp=scan.nextLine();
		}
		pollStation = new PollStation(voter,party);
		pollStation.startElection();
		scan.close();
	}
	
	public static Party getParty(String name,Party party[])
	{
		int i;
		for(i=0;i<party.length;i++)
		{
			if(party[i].getName().equals(name))
				return party[i];
		}
		return null;
	}
	
	public static Candidate getCandidate(String name,Candidate candidate[])
	{
		int i;
		for(i=0;i<candidate.length;i++)
		{
			if(candidate[i].getName().equals(name))
				return candidate[i];
		}
		return null;
	}
}