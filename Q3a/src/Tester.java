import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Employee e[];
		Scanner Scan = new Scanner(new File("EmployeeInfo.txt"));
		int length;
		for(length=0;Scan.hasNextLine();length++)
			Scan.nextLine();
		Scan.close();
		Scanner scan = new Scanner(new File("EmployeeInfo.txt"));
		length = length/4;
		e = new Employee[length];
		for(int i=0;i<length;i++)
		{
			String s = scan.nextLine();
			String ss[] = s.split(",");
			s=scan.nextLine();
			String c[] = s.split(",");
			s=scan.nextLine();
			String ml[] = s.split(",");
			s=scan.nextLine();
			String cl[] = s.split(",");
			e[i] = new Employee(Integer.parseInt(ss[0]),ss[1],Integer.parseInt(c[1]),Integer.parseInt(ml[1]),Integer.parseInt(cl[1]));
		}
		scan.close();
		scan = new Scanner(new File("Employee Attendance Log Month 1.txt"));
		scan.nextLine();
		while(scan.hasNextLine())
		{
			String s = scan.nextLine();
			String ss[] = s.split(",");
			getEmployee(Integer.parseInt(ss[2]),e).Attendance(ss[0],ss[1]);
		}
		scan.close();
		for(int i=0;i<e.length;i++)
			e[i].printInfo();
	}
	
	public static Employee getEmployee(int ID,Employee e[])
	{
		int i;
		for(i=0;i<e.length;i++)
			if(e[i].getID()==ID)
				break;
		return e[i];
	}
}