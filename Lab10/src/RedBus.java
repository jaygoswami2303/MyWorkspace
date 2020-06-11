/** 
 * Problem 9
 * @author Jay Goswami
 * SID:    201501037
 */

public class RedBus extends Bus
{
	private static int totalFuelLost;//Total Fuel lost by buses of type Red
	
	public RedBus(int number)
	{
		super(number,"Red",6,40);
	}
	
	public static void setTotalFuelLost(int fuelLost)
	{
		totalFuelLost = totalFuelLost + fuelLost;
	}
	
	public static int getTotalFuelLost()
	{
		return totalFuelLost;
	}
}