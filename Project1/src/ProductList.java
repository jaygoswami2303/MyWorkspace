/** 
 * Project 1
 * @author Jay Goswami
 * SID:    201501037
 */

import java.util.ArrayList;

public class ProductList
{
	private ArrayList<Product> products;//List of all products
	
	public ProductList()
	{
		this.products = new ArrayList<Product>();
	}
	
	public void addProduct(Product product)//Adds product to productList
	{
		this.products.add(product);
	}
	
	public ArrayList<Product> search(char searchType,String search)//Searches for the product
	{
		ArrayList<Product> foundProducts = new ArrayList<Product>();//List of found products
		for(int i=0;i<products.size();i++)
		{
			if(searchType=='1')//Search by Name
			{
				if(products.get(i).getName().contains(search))
					foundProducts.add(products.get(i));
			}
			else if(searchType=='2')//Search by Category
			{
				if(products.get(i).getCategory().contains(search))
					foundProducts.add(products.get(i));
			}
			else if(products.get(i).getPrice()<=Integer.parseInt(search))//Search by price
				foundProducts.add(products.get(i));
		}
		return foundProducts;//Returns list of found products
	}
	
	public void removeProduct(Product product)//Removes a product which has been sold
	{
		products.remove(product);
	}
	
	public ArrayList<Product> sort(ArrayList<Product> products,char sortByChoice,char sortInChoice)//Sorts product by price/popularity
	{
		ArrayList<Product> sortedProduct = new ArrayList<Product>();//Sorted product list
		while(!products.isEmpty())
		{
			int max = 0;
			Product product = null;
			for(int i=0;i<products.size();i++)
			{
				if(sortByChoice=='1')
				{
					if(products.get(i).getPopularity()>max)//Checks for the product with maximum popularity
					{
						product = products.get(i);
						max = products.get(i).getPopularity();
					}
				}
				else
				{
					if(products.get(i).getPrice()>max)//Checks for the product with maximum price
					{
						product = products.get(i);
						max = products.get(i).getPrice();
					}
				}
			}
			sortedProduct.add(product);//Product with maximum price/popularity gets added, so products are in descending order
			products.remove(product);
		}
		if(sortInChoice=='1')//to sort in ascending order
		{
			ArrayList<Product> newList = new ArrayList<Product>();
			for(int i=sortedProduct.size()-1;i>=0;i--)//List is in descending order
			{
				newList.add(sortedProduct.remove(i));//Reverses the list
			}
			sortedProduct = newList;//List is sorted in ascending order
		}
		for(int i=0;i<sortedProduct.size();i++)
		{
			System.out.print((i+1) + ". ");
			sortedProduct.get(i).printInfo();//Prints the details of found products
			System.out.println();
		}
		return sortedProduct;
	}
	
	public void printList()
	{
		for(int i=0;i<products.size();i++)
		{
			System.out.print((i+1) + ". ");
			products.get(i).printInfo();//Prints the details of products
			System.out.println();
		}
	}
	
	public void printCategories()
	{
		ArrayList<String> categories = new ArrayList<String>(); 
		int count=1;
		for(int i=0;i<products.size();i++)
			if(!categories.contains(products.get(i).getCategory()))
			{
				System.out.println((count++) + ". " + products.get(i).getCategory());
				categories.add(products.get(i).getCategory());
			}
		System.out.println();
	}
}