import java.util.Stack;

public class stack
{
	Stack<Character> c;
	
	public stack()
	{
		c = new Stack<Character>();
	}
	
	public boolean isBalanced(String s)
	{
		boolean ans=true;
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)==')' || s.charAt(i)==']' || s.charAt(i)=='}')
			{
				if(c.peek()==s.charAt(i)-1 || c.peek()==s.charAt(i)-2)
					c.pop();
				else
				{
					ans=false;
					break;
				}
			}
			/*if(s.charAt(i)==')')
			{
				if(c.peek()=='(')
					c.pop();
				else
				{
					ans=false;
					break;
				}
			}
			else if(s.charAt(i)=='}')
			{
				if(c.peek()=='{')
					c.pop();
				else
				{
					ans=false;
					break;
				}
			}
			else if(s.charAt(i)==']')
			{
				if(c.peek()=='[')
					c.pop();
				else
				{
					ans=false;
					break;
				}
			}*/
			else
				c.push(s.charAt(i));
		}
		return ans;
	}
}