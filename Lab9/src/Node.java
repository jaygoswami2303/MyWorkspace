public class Node
{
	private String name;
	private int tenure;
	private int salary;
	private int id;
	private Node left;
	private Node right;
	
	public Node(String name,int tenure,int salary,int id)
	{
		this.name = name;
		this.tenure = tenure;
		this.salary = salary;
		this.id = id;
		this.left = null;
		this.right = null;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getTenure()
	{
		return this.tenure;
	}
	
	public int getSalary()
	{
		return this.salary;
	}
	
	public int getid()
	{
		return this.id;
	}
	
	public Node getLeft()
	{
		return this.left;
	}
	
	public Node getRight()
	{
		return this.right;
	}
	
	public void setLeft(Node left)
	{
		this.left = left;
	}
	
	public void setRight(Node right)
	{
		this.right = right;
	}
}