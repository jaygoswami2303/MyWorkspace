/** 
 * Project 1
 * @author Jay Goswami
 * SID:    201501037
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		ProductList products = new ProductList();
		ArrayList<User> users = new ArrayList<User>();
		Scanner scan = new Scanner(new File("product_list.txt"));//File containing products
		int length = scan.nextInt();
		scan.nextLine();
		for(int i=0;i<length;i++)
		{
			String nL = scan.nextLine();
			String ss[] = nL.split(",");
			Product product = new Product(ss[0],Integer.parseInt(ss[1]),ss[2],Integer.parseInt(ss[3]));
			products.addProduct(product);//Add products to product list from file
		}
		scan.close();
		
		scan = new Scanner(new File("user_list.txt"));//File containing users
		length = scan.nextInt();
		scan.nextLine();
		for(int i=0;i<length;i++)
		{
			String nL = scan.nextLine();
			String ss[] = nL.split(",");
			String address = "";
			for(int j=3;j<ss.length;j++)
				address = address + "," + ss[j];
			User user = new User(ss[0],ss[1],Integer.parseInt(ss[2]),address.substring(1));
			users.add(user);//Add user details to user ArrayList
		}
		scan.close();
		boolean exit = false;
		scan = new Scanner(System.in);
		while(!exit)
		{
			System.out.println("1. Search by name  2. Search by category  3. Search by price(maximum price) 4. List all products 5. List all Categories 0.Exit");
			String choice = scan.nextLine();
			if(choice.length()!=1)//Checks for invalid choices
			{
				System.out.println("Enter valid choice.\n");
				continue;
			}
			char searchType = choice.charAt(0);//Stores type by which to search i.e. name/category/price
			if(searchType=='0')//To end the program
				exit=true;
			else
			{
				if(searchType=='4')
				{
					products.printList();
					continue;
				}
				else if(searchType=='5')
				{
					products.printCategories();
					continue;
				}
				else if(searchType=='1')
					System.out.println("Enter a name to search:");
				else if(searchType=='2')
					System.out.println("Enter a category to search:");
				else if(searchType=='3')
					System.out.println("Enter maximum price to search:");
				else//Checks for invalid choices
				{
					System.out.println("Enter valid choice.\n");
					continue;
				}
				String search = scan.nextLine();//Name/price to search
				ArrayList<Product> foundProducts = products.search(searchType,search);//Searches for product of given name/price
				if(!foundProducts.isEmpty())//Checks if any product matches the search
				{
					System.out.println("Do you want to sort products? Y/N");
					char sortChoice = scan.nextLine().charAt(0);
					char sortByChoice = '0';//Sort by choice i.e. by price/popularity
					char sortInChoice = '0';//Sort in choice i.e. in ascending/descending order
					if(sortChoice=='Y')
					{
						System.out.println("1 - Sort by popularity\n2 - Sort by price");
						sortByChoice = scan.nextLine().charAt(0);
						if(!(sortByChoice=='1' || sortByChoice=='2'))//Checks for invalid choices
						{
							System.out.println("Enter valid choice.\n");
							continue;
						}
						System.out.println("1 - Ascending order\n2 - Descending order");
						sortInChoice = scan.nextLine().charAt(0);
						if(!(sortByChoice=='1' || sortByChoice=='2'))//Checks for invalid choices
						{
							System.out.println("Enter valid choice.\n");
							continue;
						}
					}
					else if(sortChoice!='N')//Checks for invalid choices
					{
						System.out.println("Enter valid choice.\n");
						continue;
					}
					foundProducts = products.sort(foundProducts,sortByChoice,sortInChoice);//Sorts the found product list
					System.out.println("Which product do you want to buy? Type 0 if you do not wish to buy any product");
					int productNumber = scan.nextInt();//Product number intended to be bought
					scan.nextLine();
					if(productNumber==0)
						continue;
					else if(productNumber>foundProducts.size())//Checks for invalid choices
					{
							System.out.println("Enter valid choice.\n");
							continue;
					}
					Product foundProduct = foundProducts.get(productNumber-1);
					System.out.println("Enter user ID: ");
					String userID = scan.nextLine();
					User user = null;
					for(int i=0;i<users.size();i++)
					{
						if(users.get(i).getName().equals(userID))
						{
							user = users.get(i);
							break;
						}
					}
					if(user==null)//Checks whether user exists or not
						System.out.println("User not found\n");
					else
					{
						System.out.println("Enter Password: ");
						String password = scan.nextLine();
						if(password.equals(user.getPassword()))//Checks for validity of password
						{
							boolean bought = user.buy(foundProduct);//Stores whether user bought product or not
							if(bought)
							{
								System.out.println("Product Info:");
								foundProduct.printInfo();
								System.out.println("Product is shipped to " + user.getAddress());//Prints info of product bought
								System.out.println();
								products.removeProduct(foundProduct);//Removes the bought product from product list
							}
						}
						else
							System.out.println("Password is incorrect.\n");
					}
				}
				else//If no products are found
					System.out.println("No Products found.");
			}
		}
		scan.close();
	}
}