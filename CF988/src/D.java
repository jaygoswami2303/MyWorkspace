import java.io.*;
import java.util.*;
 
public class D 
{
	
	public static void main(String[] args) 
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		ArrayList<Long> ar = new ArrayList<Long>();
		HashSet<Long> hs = new HashSet<Long>();
		for(int i=0;i<n;i++)
		{
			ar.add(ir.nextLong());
			hs.add(ar.get(i));
		}
		long flag[] = new long[32];
		flag[0] = 1;
		for(int i=1;i<32;i++)
			flag[i] = flag[i-1]<<1;
		int max = 1;
		long ans[] = new long[max];
		ans[0] = ar.get(0);
		Collections.sort(ar);
		for(int i=0;i<n-1 && max<3;i++)
		{
			for(int j=0;j<32 && max<3;j++)
			{
				if(hs.contains(ar.get(i)+flag[j]))
				{
					if(hs.contains(ar.get(i)+2*flag[j]) && max<3)
					{
						max = 3;
						ans = new long[max];
						ans[0] = ar.get(i);
						ans[1] = ar.get(i)+flag[j];
						ans[2] = ar.get(i)+2*flag[j];
					}
					else if(max<2)
					{
						max = 2;
						ans = new long[max];
						ans[0] = ar.get(i);
						ans[1] = ar.get(i)+flag[j];
					}
				}
			}
		}
		pw.println(max);
		for(int i=0;i<max;i++)
			pw.print(ans[i] + " ");
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