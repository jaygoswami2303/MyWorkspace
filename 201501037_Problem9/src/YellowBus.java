/** 
 * Problem 9
 * @author Jay Goswami
 * SID:    201501037
 */

public class YellowBus extends Bus
{
	private static int totalFuelLost;//Total Fuel lost by buses of type Yellow
	
	public YellowBus(int number)
	{
		super(number,"Yellow",4,20);
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