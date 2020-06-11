import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Party party[];
		Candidate candidate[];
		Voter voter[];
		PollStation pollStation;
		
		Scanner scan = new Scanner(new File("party"));
		int length = scan.nextInt();
		scan.nextLine();
		party = new Party[length];
		for(int i=0;i<length;i++)
		{
			String nL = scan.nextLine();
			String sS[] = nL.split(",");
			party[i] = new Party(sS[0],sS[1],sS[2]);
		}
		scan.close();
		
		scan = new Scanner(new File("candidate"));
		length = scan.nextInt();
		scan.nextLine();
		candidate = new Candidate[length];
		for(int i=0;i<length;i++)
		{
			String nL = scan.nextLine();
			String sS[] = nL.split(",");
			candidate[i] = new Candidate(sS[0],getParty(sS[1],party),sS[2]);
		}
		scan.close();
		
		scan = new Scanner(new File("voter"));
		length = scan.nextInt();
		scan.nextLine();
		voter = new Voter[length];
		for(int i=0;i<length;i++)
		{
			String nL = scan.nextLine();
			String sS[] = nL.split(",");
			voter[i] = new Voter(sS[0],getCandidate(sS[1],candidate),getCandidate(sS[2],candidate));
		}
		scan.close();
		
		System.out.println("DO you want candidate to change party? Y/N");
		scan = new Scanner(System.in);
		String cP = scan.nextLine();
		while(cP.equals("Y"))
		{
			System.out.println("Enter name of candidate: ");
			Candidate c = getCandidate(scan.nextLine(),candidate);
			if(c==null)
				System.out.println("Candidate is not registered");
			else
			{
				System.out.println("Enter party to be joined: ");
				Party p = getParty(scan.nextLine(),party);
				if(p==null)
					System.out.println("Party is not registered.");
				else
					c.changeParty(p);
			}
			System.out.println("Do you want any other candidate to change party? Y/N");
			cP = scan.nextLine();
		}
		scan.close();
		pollStation = new PollStation(party,voter);
		pollStation.startElection();
		pollStation.declareResult();
	}
	
	public static Party getParty(String name,Party p[])
	{
		for(int i=0;i<p.length;i++)
			if(p[i].getName().equals(name))
				return p[i];
		return null;
	}
	
	public static Candidate getCandidate(String name,Candidate c[])
	{
		for(int i=0;i<c.length;i++)
			if(c[i].getName().equals(name))
				return c[i];
		return null;
	}
}