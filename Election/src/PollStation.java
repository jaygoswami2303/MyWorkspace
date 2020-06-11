public class PollStation
{
	private Party party[];
	private Voter voter[];
	
	public PollStation(Party p[],Voter v[])
	{
		this.party = p;
		this.voter = v;
	}
	
	public void startElection()
	{
		for(int i=0;i<voter.length;i++)
		{
			Candidate c = voter[i].getVote();
			vote(voter[i],c);
		}
	}
	
	public void vote(Voter v,Candidate c)
	{
		c.addVote();
	}
	
	public void declareResult()
	{
		int max = party[0].countVotes();
		Party winningParty = party[0];
		for(int i=1;i<party.length;i++)
		{
			if(party[i].countVotes()>max)
			{
				max = party[i].countVotes();
				winningParty = party[i];
			}
		}
		System.out.println(winningParty.getName() + " wins the election.");
		System.out.println(winningParty.getPresident().getName() + " is the president.");
	}
}