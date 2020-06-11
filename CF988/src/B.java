import java.io.*;
import java.util.*;
 
public class B 
{
	
	public static void main(String[] args) 
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		int count[] = new int[101];
		String s[] = new String[101];
		boolean correct = true;
		int max = 0;
		for(int i=0;i<n;i++) 
		{
			String str = new String(ir.readString());
			int l = str.length();
			if(l>max)
				max = l;
			if(s[l]==null) 
			{
				s[l] = str;
				count[l]++;
			}
			else if(s[l].equals(str)) 
			{
				count[l]++;
			}
			else 
			{
				correct = false;
			}
		}
		if(correct)
		{
			for(int i=max-1;i>0 && correct;i--)
			{
				if(s[i]!=null) 
				{
					if(s[max].contains(s[i])) 
					{
						max = i;
					}
					else 
					{
						correct = false;
					}
				}
			}
		}
		if(correct)
		{
			pw.println("YES");
			for(int i=1;i<101;i++)
			{
				for(int j=0;j<count[i];j++)
					pw.println(s[i]);
			}
		}
		else
			pw.print("NO");
		pw.close();
	}
	
	static class InputReader 
	{
 
		private final InputStream stream;
		private final byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;
 
		public InputReader(InputStream stream) 
		{
			this.stream = stream;
		}
 
		public int snext() 
		{
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars) 
			{
				curChar = 0;
				try 
				{
					snumChars = stream.read(buf);
				} catch (IOException e) 
				{
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}
 
		public int nextInt() 
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do 
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public long nextLong() 
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-') 
			{
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do 
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public int[] nextIntArray(int n) 
		{
			int a[] = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
 
		public String readString() 
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do 
			{
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}
 
		public boolean isSpaceChar(int c) 
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
		public interface SpaceCharFilter 
		{
			public boolean isSpaceChar(int ch);
		}
	}
}