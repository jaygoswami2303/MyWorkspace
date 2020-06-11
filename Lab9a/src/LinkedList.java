public class LinkedList<Type>
{
	Node<Type> 	head;
	
	public LinkedList()
	{
		head = null;
	}
	
	public void addElement(Type x)
	{
		Node<Type> temp = head;
		if(head!=null)
		{
			while(temp.getNext()!=null)
				temp = temp.getNext();
			temp.setNext(new Node<Type>(x));
		}
		else
			head = new Node<Type>(x);
	}
	
	public Type removeElement()
	{
		Type x = head.getVal();
		head = head.getNext();
		return x;	
	}
	
	public void printElement()
	{
		Node<Type> temp = head;
		System.out.print("Linked List content : ");
		while(temp!=null)
		{
			System.out.println(temp.getVal());
			temp = temp.getNext();
		}
	}
}