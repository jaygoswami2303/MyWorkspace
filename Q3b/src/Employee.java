import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Employee
{
	private int ID;
	private String name;
	private Class Class;
	private int mLeave;
	private int cLeave;
	private ArrayList<String> lTaken = new ArrayList<String>();
	private int attendance[] = {0,0,0,0};
	private int pSalary;
	private int penalty;
	private int netPSalary;
	private int penaltyPD;
	
	public Employee(String ID,String n,Class c,String ml,String cl)
	{
		this.ID=Integer.parseInt(ID);
		this.name=n;
		this.Class=c;
		this.mLeave=Integer.parseInt(ml);
		this.cLeave=Integer.parseInt(cl);
		this.pSalary=this.Class.getSalary();
		this.penaltyPD=this.Class.getPenalty();
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public Class getclass()
	{
		return this.Class;
	}
	
	public void setAttendance(int s[])
	{
		this.attendance=s;
	}
	
	public void setClass(Class c)
	{
		this.Class=c;
		setSalary(Class.getSalary());
		setPenalty(Class.getPenalty());
	}
	
	public void setSalary(int s)
	{
		this.pSalary=s;
	}
	
	public void setPenalty(int p)
	{
		this.penaltyPD=p;
	}
	
	public void Attendance(String date,String leave)
	{
		if(leave.equals("P"));
		else if(leave.equals("M") && mLeave>0)
		{
			attendance[2]++;
			mLeave--;
			lTaken.add(date + " " + leave);
		}
		else if(leave.equals("C") && cLeave>0)
		{
			attendance[3]++;
			cLeave--;
			lTaken.add(date + " " + leave);
		}
		else
			attendance[1]++;
	}
	
	private void calculateSalary()
	{
		attendance[0] = 30-attendance[1]-attendance[2]-attendance[3];
		int perDay=pSalary/30;
		penalty = attendance[1]*penaltyPD;
		netPSalary = attendance[0]*perDay - penalty;
	}
	
	public void printInfo()
	{
		calculateSalary();
		System.out.println(name + "\t" + ID);
		System.out.println("Payable salary at end of month: " + pSalary);
		System.out.println("Leave (which are granted) taken by an employee: " + lTaken.size());
		for(int i=0;i<lTaken.size();i++)
			System.out.println("\t" + lTaken.get(i));
		System.out.println("Penalty amount: " + penalty);
		System.out.println("Net payable salary: " + netPSalary);
		System.out.println("Remaining leave status: ");
		System.out.println("\tMedical Leave: " + mLeave);
		System.out.println("\tCasual Leave: " + cLeave);
		System.out.println();
	}
	
	public void printToFile(BufferedWriter bw) throws IOException
	{
		if(Class.getID()!=0)
		{
			bw.write(ID + "," + name);
			bw.newLine();
			bw.write("Class," + Class);
			bw.newLine();
			bw.write("Medical," + mLeave);
			bw.newLine();
			bw.write("Casual," + cLeave);
			bw.newLine();
		}
	}
	
	public int changeClass()
	{
		int c = Class.getID();
		if(penalty>=1000)
			c = (c+1)%4;
		else if(penalty<=500)
			c = c -1 + 1/c;
		return c;
	}
}