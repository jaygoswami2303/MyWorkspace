/** 
 * Project 1
 * @author Jay Goswami
 * SID:    201501037
 */

public class User
{
	private String name;
	private String password;
	private int budget;
	private String address;
	
	public User(String name,String password,int budget,String address)
	{
		this.name = name;
		this.password = password;
		this.budget = budget;
		this.address = address;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public boolean buy(Product product)//Returns true if product is in user's budget else returns false
	{
		boolean bought = false;
		if(budget>product.getPrice())//Checks whether product is in user's budget
		{
			budget = budget - product.getPrice();//Removes the product price from user's budget(or wallet)
			bought = true;
		}
		else
			System.out.println("Product price exceeds your budget.");
		return bought;
	}
}