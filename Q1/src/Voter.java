import java.util.Random;

public class Voter
{
	private int ID;
	private Candidate CP[] = new Candidate[2];
	
	public Voter(int i,Candidate c1,Candidate c2)
	{
		this.ID=i;
		this.CP[0]=c1;
		this.CP[1]=c2;
	}
	
	public Candidate getVote()
	{
		Random random = new Random();
		int min=0;
		int max=1;
		int votePref=random.nextInt(max-min+1)+min;
		return CP[votePref];
	}
}