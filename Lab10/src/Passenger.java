/** 
 * Problem 9
 * @author Jay Goswami
 * SID:    201501037
 */

public class Passenger
{
	private int boardInTime;
	private int boardOffTime;
	private String name;
	private char origin;
	private char destination;
	private String busType;
	private int busNumber;
	
	public Passenger(String name,int in,int out,char origin,char destination)
	{
		this.name = name;
		this.boardInTime = in;
		this.boardOffTime = out;
		this.origin = origin;
		this.destination = destination;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getBoardInTime()
	{
		return this.boardInTime;
	}
	
	public int getBoardOffTime()
	{
		return this.boardOffTime;
	}
	
	public char getOrigin()
	{
		return this.origin;
	}
	
	public char getDestination()
	{
		return this.destination;
	}

	public void printInfo()//Prints details of the travel by the passenger
	{
		System.out.println(this.name + " travelled from " + this.origin + " to " + this.destination + " in " + this.busType + ", Bus Number: " + this.busNumber);
	}

	public void boardBus(Bus bus)
	{
		this.busType = bus.getColor() + " Bus";
		this.busNumber = bus.getNumber();
	}
}