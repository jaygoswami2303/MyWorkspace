/** 
 * Problem 9
 * @author Jay Goswami
 * SID:    201501037
 */

public class GreenBus extends Bus
{
	private static int totalFuelLost;//Total Fuel lost by buses of type Green
	
	public GreenBus(int number)
	{
		super(number,"Green",5,30);
		totalFuelLost = 0;            
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