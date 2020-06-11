import java.util.HashSet;
import java.util.Iterator;

public class Solution
{
	public static void main(String[] args)
	{
		HashSet hs = new HashSet();
		hs.add(2);
		hs.add(1);
		hs.add(4);
		hs.add(5);
		Iterator i = hs.iterator();
		while(i.hasNext())
			System.out.println(i.next());
		System.out.println();
		System.out.println("HashSet contains 3: " + hs.contains(3));
	}
}