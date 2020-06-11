/** 
 * Problem 9
 * @author Jay Goswami
 * SID:    201501037
 */

import java.util.ArrayList;

public class Bus
{
	private int number;
	private String color;
	private int fuelLostPerMin;
	private int totalFuelLost;
	private int passangerCapactiy;
	private ArrayList<Passenger> passengersOnBoard;
	private char currentStop;
	
	public Bus(int number,String color,int fuelLost,int capacity)
	{
		this.number = number;
		this.color = color;
		this.fuelLostPerMin = fuelLost;
		this.totalFuelLost = 0;
		this.passangerCapactiy = capacity;
		this.passengersOnBoard = new ArrayList<Passenger>();
		this.currentStop = 'A';
		this.totalFuelLost = this.fuelLostPerMin * 5;//Time waited at stop 'A'
	}
	
	public int getPassengerCapacity()
	{
		return this.passangerCapactiy;
	}
	
	public void boardInPassenger(Passenger passenger)
	{
			passengersOnBoard.add(passenger);
	}
	
	public void boardOffPassenger(Passenger passenger)
	{
		passengersOnBoard.remove(passenger);
	}
	
	public int getNumberOfPassengerOnBoard()
	{
		return this.passengersOnBoard.size();
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public int getNumber()
	{
		return this.number;
	}
	
	public char getCurrentStop()
	{
		return currentStop;
	}
	
	public int getFuelLost()
	{
		return this.totalFuelLost;
	}
	
	public boolean isFull()
	{
		if(this.passengersOnBoard.size()==this.passangerCapactiy)
			return true;
		else
			return false;
	}
	
	public void nextStop()//Buses goes to next stop and fuel is calculated for waiting at stop and travel time fuel consumption
	{
		if(currentStop!='G') //Check whether bus has reached stop 'G' or not
		{
			this.currentStop++;
			this.totalFuelLost = this.totalFuelLost + 5*this.fuelLostPerMin + this.passengersOnBoard.size();;
		}
		else//Bus has reached stop 'G',so calculates time for all passengers to board off
		{
			int time=0;
			while(!passengersOnBoard.isEmpty())
				time = time + passengersOnBoard.remove(0).getBoardOffTime();
			this.totalFuelLost = this.totalFuelLost + (int)(this.fuelLostPerMin/60.0*time);
		}
	}
	
	public ArrayList<Passenger> getPassengersOnBoard()
	{
		return this.passengersOnBoard;
	}
}