public class Class
{
	private int ID;
	private int salary;
	private int penaltyPD;
	
	public Class(int ID,int s,int p)
	{
		this.ID = ID;
		this.salary = s;
		this.penaltyPD = p;
	}
	
	public int getID()
	{
		return this.ID;
	}
	public int getSalary()
	{
		return this.salary;
	}
	
	public int getPenalty()
	{
		return this.penaltyPD;
	}
}