import java.util.Scanner;

public class Tester
{
	public static void main(String[] args)
	{
		stack c = new stack();
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		boolean isBalanced[] = new boolean[n];
		for(int i=0;i<n;i++)
		{
			String s = scan.nextLine();
			isBalanced[i] = c.isBalanced(s);
		}
		scan.close();
		for(int i=0;i<n;i++)
		{
			if(isBalanced[i])
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}