public class Resident
{
	private String name;
	private String sex;
	private int age;
	private String address;
	private String authority;
	
	public Resident(String n,String a,String s,int ag,String add)
	{
		this.name=n;
		this.authority=a;
		this.sex=s;
		this.age=ag;
		this.address=add;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getSex()
	{
		return this.sex;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public String getAuthority()
	{
		return this.authority;
	}
}