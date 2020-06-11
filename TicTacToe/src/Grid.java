public class Grid
{
	private char grid[][] = new char[3][3];
	
	public Grid()
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				grid[i][j] = '_';
	}
	
	public boolean checkWin()
	{
		for(int i=0;i<3;i++)
		{
			if(grid[i][0] == grid [i][1] && grid[i][1]==grid[i][2] && grid[i][2]!='_')
				return true;
			if(grid[0][i] == grid [1][i] && grid[1][i]==grid[2][i]  && grid[2][i]!='_')
				return true;
		}
		if(grid[0][0]==grid[1][1] && grid[1][1]==grid[2][2]  && grid[2][2]!='_')
			return true;
		if(grid[0][2]==grid[1][1] && grid[1][1]==grid[2][0] && grid[2][0]!='_')
			return true;
		return false;
	}
	
	public void newMove(char player,int a,int b)
	{
		grid[a-1][b-1]=player;
		printGrid();
	}
	
	public boolean checkValidity(char player,int a,int b)
	{
		if(a<1 || a>3 || b<1 || b>3)
			return false;
		else if(grid[a-1][b-1]=='_')
		{
			newMove(player,a,b);
			return true;
		}
		else
			return false;
	}
	
	public void printGrid()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
				System.out.print(grid[i][j] + " ");
			System.out.println();
		}
	}
}