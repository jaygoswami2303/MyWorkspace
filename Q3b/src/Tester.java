import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws IOException
	{
		Class cLass[] = new Class[4];
		cLass[0] = new Class(0,0,0);
		cLass[1] = new Class(1,50000,500);
		cLass[2] = new Class(2,60000,600);
		cLass[3] = new Class(3,70000,700);
		ArrayList<Employee> e = new ArrayList<Employee>();
		Scanner scan = new Scanner(new File("EmployeeInfo.txt"));
		while(scan.hasNextLine())
		{
			String s = scan.nextLine();
			String ss[] = s.split(",");
			s=scan.nextLine();
			String c[] = s.split(",");
			s=scan.nextLine();
			String ml[] = s.split(",");
			s=scan.nextLine();
			String cl[] = s.split(",");
			e.add(new Employee(ss[0],ss[1],cLass[Integer.parseInt(c[1])],ml[1],cl[1]));
		}
		scan.close();
		scan = new Scanner(new File("Employee Attendance Log Month 1.txt"));
		scan.nextLine();
		while(scan.hasNextLine())
		{
			String s = scan.nextLine();
			String ss[] = s.split(",");
			Employee temp = getEmployee(Integer.parseInt(ss[2]),e);
			if(temp!=null)
				temp.Attendance(ss[0],ss[1]);
		}
		scan.close();
		System.out.println("1st Month:");
		for(int i=0;i<e.size();i++)
			e.get(i).printInfo();
		for(int i=0;i<e.size();i++)
			e.get(i).setClass(cLass[e.get(i).changeClass()]);
		FileWriter fw = new FileWriter("abc.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i=0;i<e.size();i++)
			e.get(i).printToFile(bw);
		bw.close();
		fw.close();
		for(int i=0;i<e.size();i++)
		{
			int a[] = {0,0,0,0};
			if(e.get(i).getclass().equals(cLass[0]))
				e.remove(i);
			else
				e.get(i).setAttendance(a);
		}
		scan = new Scanner(new File("Employee Attendance Log Month 2.txt"));
		scan.nextLine();
		while(scan.hasNextLine())
		{
			String s = scan.nextLine();
			String ss[] = s.split(",");
			Employee temp = getEmployee(Integer.parseInt(ss[2]),e);
			if(temp!=null)
				temp.Attendance(ss[0],ss[1]);
		}
		scan.close();
		System.out.println("2nd Month");
		for(int i=0;i<e.size();i++)
			e.get(i).printInfo();
	}
	
	public static Employee getEmployee(int ID,ArrayList<Employee> e)
	{
		int i;
		for(i=0;i<e.size();i++)
			if(e.get(i).getID()==ID)
				return e.get(i);
		return null;
	}
}