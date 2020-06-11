public class Stack<Type>
{
	Node<Type> topNode;
	
	public Stack()
	{
		topNode = null;
	}
	
	public void push(Type x)
	{
		Node<Type> temp = new Node<Type>(x);
		temp.setNext(topNode);
		topNode = temp;
	}
	
	public Type pop()
	{
		Type x;
		if(topNode==null)
			x = null;
		else
		{
			x = topNode.getVal();
			topNode = topNode.getNext();
		}
		return x;
	}
	
	public Stack<Type> reverse()
	{
		Stack<Type> ans = new Stack<Type>();
		Type x;
		while((x=this.pop())!=null)
			ans.push(x);
		return ans;
		
	}
}