import java.io.*;
import java.util.*;
 
public class STDDEV
{
	
	public static void main(String[] args)
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t = ir.nextInt();
		while(t-->0) {
			int n = ir.nextInt();
			int s = ir.nextInt();
			if(n!=1) {
				float temp = (((float)s)*s)*n;
				if(n%2!=0) {
					n--;
					pw.print("0 ");
				}
				temp /= n;
				temp = (float) Math.sqrt(temp);
				for(int i=0;i<n;i++) {
					if(i%2==0)
						pw.print(temp + " ");
					else {
						if(temp==0)
							pw.print(temp + " ");
						else
							pw.print((-1*temp) + " ");
					}
				}
			}
			else {
				if(n==0)
					pw.print("0");
				else
					pw.print("-1");
			}
			pw.println();
		}
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
				}
				catch (IOException e)
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