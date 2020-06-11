import java.util.Scanner;

public class Grid
{
	private char grid[][];
	private int m,n;
	public Grid()
	{
		Scanner scan = new Scanner(System.in);
		m = scan.nextInt();
		n = scan.nextInt();
		grid = new char[m][n];
		String b;
		for(int i=0;i<m;i++)
		{
			b=scan.next();
			for(int j=0;j<n;j++)
				grid[i][j]=b.charAt(j);
		}
		scan.close();
	}
	
	public void changeGeneration()
	{
		char temp[][] = new char[m][n];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				temp[i][j]=grid[i][j];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				int count=0;
				for(int o=Math.max(0, i-1);o<Math.min(m, i+2);o++)
				{
					for(int p=Math.max(0, j-1);p<Math.min(n, j+2);p++)
					{
						if((o!=i || p!=j) && temp[o][p]=='*')
							count++;
					}
				}
				if(temp[i][j]=='*')
				{
					switch(count)
					{
					case 0:
					case 1:
						grid[i][j]='-';
					case 2:
					case 3:
						break;
					default:
						grid[i][j]='-';
					}
				}
				else if(count==3)
					grid[i][j]='*';
			}
		}
	}
	
	public void printGrid()
	{
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
				System.out.print(grid[i][j]);
			System.out.println();
		}
	}
}
