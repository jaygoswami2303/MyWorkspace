public class Tester
{
	public static void main(String[] args)
	{
		System.out.println("LinkedList :");
		LinkedList<Integer> l = new LinkedList<Integer>();
		String s = "";
		for(int i=1;i<7;i++)
		{
			l.addElement(i);
			s = s + ',' + i;
		}
		s = s.substring(1);
		System.out.println("enter elements in LinkedList : " + s);
		l.printElement();
		System.out.println();
		
		System.out.println("Queue using LinkedList :");
		Queue<Integer> q = new Queue<Integer>();
		s = "EnQueue : ";
		for(int i=1;i<7;i++)
		{
			q.enQueue(i);
			s = s + i + ',';
		}
		s = s.substring(0,s.length()-1);
		System.out.println(s);
		s = "DeQueue: ";
		for(int i=1;i<7;i++)
			s = s + q.deQueue() + ',';
		s = s.substring(0,s.length()-1);
		System.out.println(s);
		System.out.println();
		
		System.out.println("Queue using Stack :");
		Queue2<Integer> q2 = new Queue2<Integer>();
		s = "EnQueue : ";
		for(int i=1;i<7;i++)
		{
			q2.enQueue(i);
			s = s + i + ',';
		}
		s = s.substring(0,s.length()-1);
		System.out.println(s);
		s = "DeQueue: ";
		for(int i=1;i<7;i++)
			s = s + q2.deQueue() + ',';
		s = s.substring(0,s.length()-1);
		System.out.println(s);
	}
}