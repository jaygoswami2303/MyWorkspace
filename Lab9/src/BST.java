public class BST
{
	private Node root;
	
	public BST()
	{
		this.root = null;
	}
	
	public void add(String name,int tenure,int salary,int id)
	{
		Node newNode = new Node(name,tenure,salary,id);
		if(root==null)
			root = newNode;
		else
		{
			Node temp = root;
			boolean found = true;
			while(found)
			{
				if(tenure<temp.getTenure())
				{
					if(temp.getLeft()!=null)
						temp = temp.getLeft();
					else
					{
						temp.setLeft(newNode);
						found=false;
					}
				}
				else if(tenure>temp.getTenure())
				{
					if(temp.getRight()!=null)
						temp = temp.getRight();
					else
					{
						temp.setRight(newNode);
						found=false;
					}
				}
				else
				{
					 if(salary>temp.getSalary())
					{
						if(temp.getRight()!=null)
							temp = temp.getRight();
						else
						{
							temp.setRight(newNode);
							found=false;
						}
					}
					else
					{
						if(temp.getLeft()!=null)
							temp = temp.getLeft();
						else
						{
							temp.setLeft(newNode);
							found=false;
						}
					}
				}
			}
		}
	}
	
	public void search(int tenure,int salary)
	{
		Node temp = root;
		boolean found = false;
		while(temp!=null)
		{
			if(temp.getTenure()==tenure)
			{
				if(salary==temp.getSalary())
				{
					found = true;
					System.out.print(temp.getid() + " ");
					temp = temp.getLeft();
				}
				else if(salary<temp.getSalary())
					temp = temp.getLeft();
				else
					temp = temp.getRight();			
			}
			else if(tenure<temp.getTenure())
				temp = temp.getLeft();
			else
				temp = temp.getRight();
		}
		if(!found)
			System.out.print("NOT FOUND");
		System.out.println();
	}	
}