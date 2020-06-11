public class string
{
	public static String add(String s1,String s2)
	{
		int m = s1.length()-1;
		int n = s2.length()-1;
		String ans = new String("0" + (m>n?s1:s2));
		int o = ans.length()-1;
		char carry='0';
		while(m>=0 && n>=0)
		{
			char b = (char) (s1.charAt(m) + s2.charAt(n)-96 + carry);
			if(b>57)
			{
				carry = '1';
				b = (char) (b-10);
			}
			else
				carry='0';
			if(o==ans.length()-1)
				ans = ans.substring(0,o) + b;
			else if (o==0)
				ans = b + ans.substring(o+1);
			else
				ans = ans.substring(0,o) + b + ans.substring(o+1);
			m--;
			n--;
			o--;
		}
		if(m<0)
		{
			while(n>=0)
			{
				char b = (char) (s2.charAt(n) -48 + carry);
				if(b>57)
				{
					carry = '1';
					b = (char) (b-10);
				}
				else
					carry='0';
				String abc;
				if(o==ans.length()-1)
					abc = ans.substring(0,o) + b;
				else if (o==0)
					abc = b + ans.substring(o+1);
				else
					abc = ans.substring(0,o) + b + ans.substring(o+1);
				ans=abc;
				n--;
				o--;
			}
		}
		else if(n<0)
		{
			while(m>=0)
			{
				char b = (char) (s1.charAt(m) -48 + carry);
				if(b>57)
				{
					carry = '1';
					b = (char) (b-10);
				}
				else
					carry='0';
				String abc;
				if(o==ans.length()-1)
					abc = ans.substring(0,o) + b;
				else if (o==0)
					abc = b + ans.substring(o+1);
				else
					abc = ans.substring(0,o) + b + ans.substring(o+1);
				ans=abc;
				m--;
				o--;
			}
		}
		if(carry=='1')
		{
			String abc;
			if(o==ans.length()-1)
				abc = ans.substring(0,o) + carry;
			else if (o==0)
				abc = carry + ans.substring(o+1);
			else
				abc = ans.substring(0,o) + carry + ans.substring(o+1);
			ans=abc;
		}
		if(ans.charAt(0)=='0')
			ans = ans.substring(1);
		return ans;
	}
	
	public static String Add(String s1,String s2)
	{
		String ans="";
		int ls1 = s1.length();
		int ls2 = s2.length();
		if(ls1>ls2)
		{
			for(int i=0;i<ls1-ls2;i++)
				s2 = "0" + s2;
		}
		else if(ls2>ls1)
		{
			for(int i=0;i<ls2-ls1;i++)
				s1 = "0" + s1;
			ls1=ls2;
		}
		char carry = '0';
		for(int k=ls1-1;k>=0;k--)
		{
			char ld = (char)(s1.charAt(k) + s2.charAt(k) + carry - 96);
			if(ld>57)
			{
				ld=(char)(ld-10);
				carry='1';
			}
			else
				carry='0';
			ans = ld + ans;
		}
		if(carry=='1')
			ans=carry+ans;
		return ans;
	}
}