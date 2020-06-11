/** 
 * Problem 9
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
		ArrayList<Bus> buses = new ArrayList<Bus>();
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();//contains all Passengers of file
		ArrayList<Passenger> passengersAtBusStop = new ArrayList<Passenger>();
		ArrayList<Passenger> passengersInBus = new ArrayList<Passenger>();
		Scanner scan = new Scanner(new File("ghi.txt"));//File containing passenger details
		while(scan.hasNextLine())
		{
			String nL = scan.nextLine();
			String ss[] = nL.split(" ");
			Passenger newPassenger;
			if(ss[0].equals("senior"))
			{
				newPassenger = new SeniorCitizen(ss[1],ss[2].charAt(0),ss[3].charAt(0));
			}
			else if(ss[0].equals("Child"))
			{
				newPassenger = new Child(ss[1],ss[2].charAt(0),ss[3].charAt(0));
			}
			else
			{
				newPassenger = new Adult(ss[1],ss[2].charAt(0),ss[3].charAt(0));
			}//Passenger gets created according to its type
			passengersAtBusStop.add(newPassenger);//Passenger enters origin stop
			passengers.add(newPassenger);
		}
		scan.close();
		for(int buscount=1;!passengersAtBusStop.isEmpty() || !passengersInBus.isEmpty() || buscount<buses.size()+7;buscount++)//Loop runs till all passenger reaches destination and all generated buses reach stop 'G'
		{
			if(!passengersAtBusStop.isEmpty())//Generates bus only if any passenger is still waiting at any stop
			{
				if(buscount%3==1)
					buses.add(new RedBus(buscount));
				else if(buscount%3==2)
					buses.add(new GreenBus(buscount));
				else
					buses.add(new YellowBus(buscount));
			}
			for(int i=1;i<=buses.size();i++)//Each generated bus waits at it current stop 
			{
				int time=0;//Time at current stop for boarding off Passenger from gate 2
				ArrayList<Passenger> inBus = buses.get(i-1).getPassengersOnBoard();//Passengers currently boarded in Bus
				for(int j=0;j<inBus.size();j++)
				{
					if(inBus.get(j).getDestination()==buses.get(i-1).getCurrentStop())//Check whether passenger in bus have reached their destination
					{
						time = time + inBus.get(j).getBoardOffTime();
						passengersInBus.remove(inBus.get(j));
						buses.get(i-1).boardOffPassenger(inBus.get(j));//Passenger is boarded off
						j--;
					}
				}
				time=0;//Time at current stop for boarding in Passenger from gate 1
				inBus = buses.get(i-1).getPassengersOnBoard();
				for(int j=0;j<passengersAtBusStop.size();j++)
				{
					if(passengersAtBusStop.get(j).getOrigin()==buses.get(i-1).getCurrentStop() && !buses.get(i-1).isFull())//Checks whether bus if full
					{
						int boardOffTime = 0;//Current passengersOnBoard's board off time at destination same as given passenger 
						for(int k=0;k<inBus.size();k++)
							if(inBus.get(k).getDestination()==passengersAtBusStop.get(j).getDestination())
								boardOffTime = boardOffTime + inBus.get(k).getBoardOffTime();
						if(passengersAtBusStop.get(j).getBoardInTime()>300-time && passengersAtBusStop.get(j).getBoardOffTime()>300-boardOffTime)//Check if time is left to board in and also if time will be left to board off
							break;
						time = time + passengersAtBusStop.get(j).getBoardInTime();
						passengersInBus.add(passengersAtBusStop.get(j));
						passengersAtBusStop.get(j).boardBus(buses.get(i-1));
						buses.get(i-1).boardInPassenger(passengersAtBusStop.remove(j));//Passenger is boarded in
						j--;
					}
				}
				if(buses.get(i-1).getCurrentStop()=='G')//Checks whether bus has reached at stop 'G'
					for(int k=0;k<inBus.size();k++)
						passengersInBus.remove(inBus.get(k));//Boards off remaining passenger
			}
			for(int i=0;i<buses.size();i++)
				buses.get(i).nextStop();//All buses travel to their next stop
		}
		for(int i=0;i<buses.size();i++)//Calculates totalFuelLost by each type of bus
		{
			int fuelLost = buses.get(i).getFuelLost();
			if(i%3==0)
				RedBus.setTotalFuelLost(fuelLost);
			else if(i%3==1)
				GreenBus.setTotalFuelLost(fuelLost);
			else
				YellowBus.setTotalFuelLost(fuelLost);
		}
		System.out.println("Fuel Lost:");
		System.out.println("\t   Red Bus    : " + RedBus.getTotalFuelLost());
		System.out.println("\t   Green Bus  : " + GreenBus.getTotalFuelLost());
		System.out.println("\t   Yellow Bus : " + YellowBus.getTotalFuelLost());
		System.out.println();
		System.out.println("Passenger Details: ");
		System.out.println();
		for(int i=0;i<passengers.size();i++)
			passengers.get(i).printInfo();
	}
}