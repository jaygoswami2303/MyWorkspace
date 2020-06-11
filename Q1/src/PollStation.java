public class PollStation
{
	private Voter voters[];
	private Party party[];
	
	public PollStation(Voter voters[],Party party[])
	{
		this.voters=voters;
		this.party=party;
	}
	
	public void startElection()
	{
		for(int i=0;i<voters.length;i++)
		{
			Candidate c = voters[i].getVote();
			vote(voters[i],c);
		}
		getResult();
	}
	
	private void vote(Voter v, Candidate c)
	{
		c.addVote();
	}
	private void getResult()
	{
		int max=party[0].getVote();
		Party win = party[0];
		for(int i=1;i<party.length;i++)
		{
			if(party[i].getVote()>max)
			{
				max = party[i].getVote();
				win = party[i];
			}
		}
		System.out.println(win.getName() + " wins the election.");
		Candidate president = win.getPresident();
		System.out.println(president.getName() + " is the president.");
	}
}