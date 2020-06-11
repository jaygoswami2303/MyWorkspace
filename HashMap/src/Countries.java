import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Countries
{
	public static void main(String[] args)
	{
		HashMap<String,String> hm = new HashMap<>();
		hm.put("AFG","Afghanistan");
		hm.put("GBR","United Kingdom of Great Britain and Northern Ireland");
		hm.put("IDN","Indonesia");
		hm.put("IND","India");
		
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		scan.close();
		String ans="null";
		if(s.length()==3)
			ans = hm.get(s);
		else if(hm.containsValue(s))
		{
			Set set = hm.entrySet();
			Iterator it = set.iterator();
			Map.Entry me;
			while(it.hasNext())
			{
				me = (Map.Entry) it.next();
				if(me.getValue().equals(s))
				{
					ans = (String) me.getKey();
					break;
				}
			}
		}
		System.out.println(ans);
	}
}