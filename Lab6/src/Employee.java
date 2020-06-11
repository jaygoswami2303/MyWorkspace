public class Employee
{
	private int ID;
	private String name;
	private int tenure;
	private int salary;
	private String office;
	
	public Employee(String id,String n,String t,String s)
	{
		this.ID=Integer.parseInt(id);
		this.name=n;
		this.tenure= Integer.parseInt(t);
		this.salary=Integer.parseInt(s);
		this.office="HQ";
	}
	
	public int getID()
	{
		return this.ID;
	}

	public int getTenure()
	{
		return this.tenure;
	}
	
	public int getSalary()
	{
		return this.salary;
	}
	
	public String getOffice()
	{
		return this.office;
	}
	public void setOffice(String o)
	{
		this.office=o;
	}
}