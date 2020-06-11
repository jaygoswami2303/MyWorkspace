public class Queue2<Type>
{
	Stack<Type> queue;
	
	public Queue2()
	{
		queue  = new Stack<Type>();
	}
	
	public void enQueue(Type x)
	{
		queue.push(x);
	}
	
	public Type deQueue()
	{
		Type x;
		queue = queue.reverse();
		x = queue.pop();
		queue = queue.reverse();
		return x;
	}
}