public class Stack
{
	char c[] = new char[100];
	int position=-1;
	
	public void push(char c)
	{
		position++;
		this.c[position]=c;
	}
	
	public void pop()
	{
		position--;
	}
	
	public char peek()
	{
		if(position==-1)
			return 'c';
		return c[position];
	}
	
	public boolean isEmpty()
	{
		if(position==-1)
			return true;
		else
			return false;
	}
}
