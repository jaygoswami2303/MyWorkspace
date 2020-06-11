public class Candidate
{
	private String name;
	private int age;
	private Party party;
	private int votes;
	
	public Candidate(String name,Party p,String age)
	{
		this.name = name;
		this.party = p;
		this.age = Integer.parseInt(age);
		this.votes = 0;
		p.register(this);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public void setParty(Party p)
	{
		this.party = p;
	}
	
	public void changeParty(Party p)
	{
		if(p.getMinAge()>=this.age && p.getMaxAge()<=this.age)
		{
			party.remove(this);
			setParty(p);
			party.register(this);
		}
		else
			System.out.println("Candidate does not satisfy the eligible age criteria.");
	}
	
	public void addVote()
	{
		this.votes++;
		party.addVote();
	}
	
	public int countVote()
	{
		return this.votes;
	}
}