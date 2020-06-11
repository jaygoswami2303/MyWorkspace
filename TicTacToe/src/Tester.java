import java.util.Scanner;

public class Tester
{
	public static void main(String[] args)
	{
		Grid game = new Grid();
		Scanner scan = new Scanner(System.in);
		int i;
		for(i=0;i<9;i++)
		{
			char c = (char) ('O' + ('X'-'O')*((i+1)%2));
			int a = scan.nextInt();
			int b = scan.nextInt();
			System.out.println();
			System.out.println("Step " + (i+1) + ":");
			while(!game.checkValidity(c,a,b))
			{
				System.out.println("Invalid Step");
				a = scan.nextInt();
				b = scan.nextInt();
			}
			if(game.checkWin())
			{
				System.out.println("Player#" +(i%2+1)+ " wins");
				break;
			}
		}
		if(i==9)
			System.out.println("Game Draw");
		scan.close();
	}
}