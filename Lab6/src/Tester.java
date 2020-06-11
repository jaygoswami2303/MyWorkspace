import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("in1"));
		int length = scan.nextInt();
		scan.nextLine();
		Employee e[] = new Employee[length];
		for(int i=0;i<length;i++)
		{
			String nl = scan.nextLine();
			String s[] = nl.split(",");
			e[i] = new Employee(s[0],s[1],s[2],s[3]);
		}
		int m = scan.nextInt();
		int k = scan.nextInt();
		scan.nextLine();
		Employee emp[] = new Employee[m];
		for(int i=0;i<m;i++)
		{
			int id = scan.nextInt();
			emp[i] = getEmployee(id,e);
		}
		scan.close();
		if(m>k)
			sort(emp);
		for(int i=0;i<k;i++)
				emp[i].setOffice("NEW");
		System.out.println("HQ");
		for(int i=0;i<e.length;i++)
			if(e[i].getOffice().equals("HQ"))
				System.out.println(e[i].getID());
		System.out.println("NEW");
		for(int i=0;i<e.length;i++)
			if(e[i].getOffice().equals("NEW"))
				System.out.println(e[i].getID());
	}
	
	public static Employee getEmployee(int ID,Employee e[])
	{
		int i;
		for(i=0;i<e.length;i++)
			if(e[i].getID()==ID)
				break;
		return e[i];
	}
	
	public static void sort(Employee e[])
	{
		for(int i=0;i<e.length-1;i++)
		{
			for(int j=i;j>=0;j--)
			{
				Employee emp;
				if(e[j].getTenure()<e[j+1].getTenure())
				{
					emp=e[j];
					e[j]=e[j+1];
					e[j+1]=emp;
				}
				else if(e[j].getTenure()==e[j+1].getTenure())
				{
					if(e[j].getSalary()<e[j+1].getSalary())
					{
						emp=e[j];
						e[j]=e[j+1];
						e[j+1]=emp;
					}
				}
			}
		}
	}
}