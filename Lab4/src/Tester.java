public class Tester
{
	public static void main(String[] args)
	{
		Grid cellLife = new Grid();
		for(int i=1;i<=3;i++)
		{
			cellLife.changeGeneration();
			System.out.println("At step=" + i +":");
			cellLife.printGrid();
			System.out.println();
		}
	}
}
