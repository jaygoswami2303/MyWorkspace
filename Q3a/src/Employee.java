import java.util.ArrayList;

public class Employee
{
	private int ID;
	private String name;
	private int Class;
	private int mLeave;
	private int cLeave;
	private ArrayList<String> lTaken = new ArrayList<String>();
	private int attendance[] = {0,0,0,0};
	private int pSalary;
	private int penalty;
	private int netPSalary;
	private int penaltyPD;
	
	public Employee(int ID,String n,int c,int ml,int cl)
	{
		this.ID=ID;
		this.name=n;
		this.Class=c;
		this.mLeave=ml;
		this.cLeave=cl;
		if(Class==1)
		{
			pSalary=50000;
			penaltyPD=500;
		}
		else if(Class==2)
		{
			pSalary=60000;
			penaltyPD=600;
		}
		else
		{
			pSalary=70000;
			penaltyPD=700;
		}
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public void Attendance(String date,String leave)
	{
		if(leave.equals("P"));
		else if(leave.equals("M") && mLeave>0)
		{
			attendance[2]++;
			mLeave--;
			String a = date + " " + leave;
			lTaken.add(a);
		}
		else if(leave.equals("C") && cLeave>0)
		{
			attendance[3]++;
			cLeave--;
			String a = date + " " + leave;
			lTaken.add(a);
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
		System.out.println("Leave (which are granted) taken by an employee: ");
		for(int i=0;i<lTaken.size();i++)
			System.out.println("\t" + lTaken.get(i));
		System.out.println("Penalty amount: " + penalty + attendance[1]);
		System.out.println("Net payable salary: " + netPSalary);
		System.out.println("Remaining leave status: ");
		System.out.println("\tMedical Leave: " + mLeave);
		System.out.println("\tCasual Leave: " + cLeave);
		System.out.println();
	}
}