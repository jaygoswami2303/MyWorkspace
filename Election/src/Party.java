import java.util.ArrayList;

public class Party
{
	private String name;
	private int minAge;
	private int maxAge;
	private ArrayList<Candidate> candidate = new ArrayList<Candidate>();
	private int votes;
	
	public Party(String name,String minA,String maxA)
	{
		this.name = name;
		this.minAge = (Integer.parseInt(minA));
		this.maxAge = (Integer.parseInt(maxA));
		this.votes = 0;
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
	
	public void register(Candidate c)
	{
		candidate.add(c);
	}
	
	public void remove(Candidate c)
	{
		candidate.remove(c);
	}
	
	public void addVote()
	{
		this.votes++;
	}
	
	public int countVotes()
	{
		return this.votes;
	}
	
	public Candidate getPresident()
	{
		int max = candidate.get(0).countVote();
		Candidate president = candidate.get(0);
		for(int i=1;i<candidate.size();i++)
		{
			if(candidate.get(i).countVote()>max)
			{
				max = candidate.get(i).countVote();
				president = candidate.get(i);
			}
			else if(candidate.get(i).countVote()==max)
			{
				if(candidate.get(i).getAge()>president.getAge())
				{
					max = candidate.get(i).countVote();
					president = candidate.get(i);
				}
				else if(candidate.get(i).getAge()==president.getAge())
				{
					if(candidate.get(i).getName().compareToIgnoreCase(president.getName())<0)
					{
						max = candidate.get(i).countVote();
						president = candidate.get(i);
					}
				}
			}
		}
		return president;
	}
}