public class Candidate
{
	private int ID;
	private String name;
	private Party party;
	private int age;
	private int votes=0;
	
	public Candidate(int i,String n, Party p, int a)
	{
		this.ID=i;
		this.name=n;
		this.party=p;
		this.age=a;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void addVote()
	{
		votes++;
		party.addVote();
	}
	
	public int getVote()
	{
		return this.votes;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public void changeParty(Party p)
	{
		if(p.getMinAge()<=age && age<=p.getMaxAge())
		{
			party.remove(this);
			this.party=p;
			party.register(this);
		}
		else
			System.out.println("Sorry the selected candidate does not have eligible age");
	}
}