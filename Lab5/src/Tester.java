import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Resident r[];
		Scanner Scan = new Scanner(new File("abc.dat"));
		int length;
		for(length=0;Scan.hasNextLine();length++)
			Scan.nextLine();
		Scan.close();
		length = length/5;
		r = new Resident[length];
		Scanner scan = new Scanner(new File("abc.dat"));
		for(int i=0;i<length;i++)
		{
			String n = scan.nextLine();
			String st = scan.nextLine();
			String ss[] = st.split(" ");
			String s = scan.nextLine();
			int ag = Integer.parseInt(scan.nextLine());
			String add = scan.nextLine();
			r[i] = new Resident(n,ss[1],s,ag,add);
		}
		scan.close();
		scan = new Scanner(System.in);
		String name = scan.nextLine();
		Resident rr = toResident(name,r);
		if(rr.getAuthority().equals("yes"))
		{
			if(rr.getSex().equals("female"))
			{
				int count =0;
				for(int i=0;i<length;i++)
				{
					if(rr.getAge()<r[i].getAge())
						count++;
				}
				System.out.println(count);
			}
			else
			{
				int max=rr.getAge();
				for(int i=0;i<length;i++)
				{
					if(r[i].getSex().equals("male") && r[i].getAge()>max)
						max=r[i].getAge();
				}
				System.out.println(max);
			}
			if(rr.getAge()>12 && rr.getAge()<20)
			{
				String add = scan.nextLine();
				for(int i=0;i<length;i++)
				{
					if(r[i].getAddress().equals(add))
						System.out.println(r[i].getName());
				}
			}
		}
		else
			System.out.println("Unauthorized Access");
	}
	
	public static Resident toResident(String name,Resident r[])
	{
		int i;
		for(i=0;i<r.length;i++)
		{
			if(r[i].getName().equals(name))
				break;
		}
		return r[i];
	}
}