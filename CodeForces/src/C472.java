import java.io.*;
import java.util.*;
 
public class C472 
{
	
	public static void main(String[] args) 
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		String fn[] = new String[n];
		String ln[] = new String[n];
		for(int i=0;i<n;i++) 
		{
			fn[i] = ir.readString();
			ln[i] = ir.readString();
		}
		int p[] = new int[n];
		for(int i=0;i<n;i++)
			p[i] = ir.nextInt()-1;
		int index = p[0];
		boolean sfn1 = fn[index].compareTo(ln[index])<0;
		boolean sfn2;
		boolean possible = true;
		for(int i=1;i<n && possible;i++) 
		{
			index = p[i];
			String temp = sfn1?fn[p[i-1]]:ln[p[i-1]];
			sfn2=fn[index].compareTo(ln[index])<0;
			if(sfn2)
			{
				sfn2=false;
				if(temp.compareTo(fn[index])<0)
						sfn2 = true;
				else if(temp.compareTo(ln[index])>0)
					possible = false;
			}
			else
			{
				sfn2=true;
				if(temp.compareTo(ln[index])<0)
					sfn2 = false;
				else if(temp.compareTo(fn[index])>0)
					possible = false;
			}
			sfn1 = sfn2;
		}
		if(possible)
			pw.print("YES");
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
			} 
			while (!isSpaceChar(c));
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