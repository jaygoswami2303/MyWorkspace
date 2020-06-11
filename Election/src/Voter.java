import java.util.Random;

public class Voter
{
	private String name;
	private Candidate cP[] = new Candidate[2];
	
	public Voter(String name,Candidate cp1,Candidate cp2)
	{
		this.name = name;
		this.cP[0] = cp1;
		this.cP[1] = cp2;
	}
	
	public Candidate getVote()
	{
		int min = 0;
		int max = 1;
		Random random = new Random();
		int votePref = random.nextInt(max-min+1) + min;
		return cP[votePref];
	}
}