/** 
 * Project 1
 * @author Jay Goswami
 * SID:    201501037
 */

public class Product
{
	private String name;
	private int price;
	private String category;
	private int popularity;
	
	public Product(String name,int price,String category,int popularity)
	{
		this.name = name;
		this.price = price;
		this.category = category;
		this.popularity = popularity;
	}
	
	public int getPrice()
	{
		return this.price;
	}
	
	public int getPopularity()
	{
		return this.popularity;
	}
	
	public String getCategory()
	{
		return this.category;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void printInfo()
	{
		System.out.println("\tName       : " + name);
		System.out.println("\tPrice      : " + price);
		System.out.println("\tCategory   : " + category);
		System.out.println("\tPopularity : " + popularity + (" (number of times searched for)"));
	}
}