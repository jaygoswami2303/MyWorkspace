public class Molecule
{
	private char name;
	private String mass;
	
	public Molecule(char n,String m)
	{
		this.name=n;
		this.mass=m;
	}
	
	public char getName()
	{
		return this.name;
	}
	
	public String getMass()
	{
		return this.mass;
	}
}