import java.util.Scanner;

public class Tester
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		boolean isBalanced[] = new boolean[n];
		for(int i=0;i<n;i++)
		{
			String s = scan.nextLine();
			Stack c = new Stack();
			boolean ans=true;
			for(int j=0;j<s.length();j++)
			{
				if(s.charAt(j)==')')
				{
					if(c.peek()=='(')
						c.pop();
					else
					{
						ans=false;
						break;
					}
				}
				else if(s.charAt(j)=='}')
				{
					if(c.peek()=='{')
						c.pop();
					else
					{
						ans=false;
						break;
					}
				}
				else if(s.charAt(j)==']')
				{
					if(c.peek()=='[')
						c.pop();
					else
					{
						ans=false;
						break;
					}
				}
				else
					c.push(s.charAt(j));
			}
			if(!c.isEmpty())
				ans=false;
			isBalanced[i]=ans;
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