public class Node<Type>
{
	private Type val;
	private Node<Type> next;
	
	public Node(Type x)
	{
		this.val = x;
		this.next = null;
	}
	
	public Type getVal()
	{
		return this.val;
	}
	
	public Node<Type> getNext()
	{
		return this.next;
	}
	
	public void setNext(Node<Type> next)
	{
		this.next = next;
	}
}