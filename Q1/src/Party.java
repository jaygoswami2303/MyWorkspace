import java.util.ArrayList;

public class Party
{
	private String name;
	private int minAge;
	private int maxAge;
	private ArrayList<Candidate> candidates = new ArrayList<Candidate>();
	private int votes;
	
	public Party(String n,int min,int max)
	{
		this.name=n;
		this.minAge=min;
		this.maxAge=max;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getMinAge()
	{
		return this.minAge;
	}
	
	public int getMaxAge()
	{
		return this.maxAge;
	}
	
	public void register(Candidate candidate)
	{
		candidates.add(candidate);
	}
	
	public void remove(Candidate candidate)
	{
		candidates.remove(candidate);
	}
	
	public void addVote()
	{
		votes++;
	}
	
	public int getVote()
	{
		return this.votes;
	}
	
	public Candidate getPresident()
	{
		Candidate president = candidates.get(0);
		int max = president.getVote();
		for(int i=1;i<candidates.size();i++)
		{
			Candidate c = candidates.get(i);
			if(c.getVote()>max)
			{
				max=c.getVote();
				president = c;
			}
			else if(c.getVote()==max)
			{
				if(c.getAge()>president.getAge())
				{
					max=c.getVote();
					president=c;
				}
				else if(c.getAge()==president.getAge())
				{
					if(c.getName().compareTo(president.getName())<0)
					{
						max=c.getVote();
						president=c;
					}
				}
			}
		}
		return president;
	}
}