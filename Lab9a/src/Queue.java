public class Queue<Type>
{
	LinkedList<Type> queue;
	
	public Queue()
	{
		queue = new LinkedList<Type>();
	}
	
	public void enQueue(Type x)
	{
		queue.addElement(x);
	}
	
	public Type deQueue()
	{
		Type x = queue.removeElement();
		return x;
	}
}