import java.io.*;
import java.util.*;
 
public class C 
{
	
	public static void main(String[] args) 
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int k = ir.nextInt();
		int n[] = new int[k];
		int sum[] = new int[k];
		HashMap<Integer,Integer> hs[] = new HashMap[k];
		Pair sums[] = new Pair[k];
		for(int i=0;i<k;i++)
		{
			n[i] = ir.nextInt();
			sum[i] = 0;
			hs[i] = new HashMap<Integer,Integer>();
			for(int j=0;j<n[i];j++)
			{
				int t = ir.nextInt();
				hs[i].put(t, j+1);
				sum[i] += t;
			}
			sums[i] = new Pair(n[i], i);
		}
		Arrays.sort(sums);
		boolean found = false;
		int i=0,x=0,j=0,y=0;
		for(int a1=0;a1<k+1 && !found;a1++)
		{
			for(int b1=a1+1;b1<k && !found;b1++)
			{
				int a = sums[a1].y;
				int b = sums[b1].y;
				int diff = sum[b]-sum[a];
				Iterator<Integer> it = hs[a].keySet().iterator();
				while(it.hasNext()) {
					int num = it.next();
					if(hs[b].containsKey(num+diff)) {
						found = true;
						i = a+1;
						x = hs[a].get(num);
						j = b+1;
						y = hs[b].get(num+diff);
						break;
					}
				}
			}
		}
		if(found)
		{
			pw.println("YES");
			pw.println(i + " " + x);
			pw.print(j + " " + y);
		}
		else
			pw.print("NO");
		pw.close();
	}
	
	static class Pair implements Comparable<Pair>
	{
		int x;
		int y;
 
		Pair(int xx,int yy)
		{
			x=xx;
			y=yy;
		}
		public String toString() 
		{
			return "[x="+this.x+", y="+this.y+"]";
		}
		@Override
		public int compareTo(Pair o) 
		{
			if(Integer.compare(this.x, o.x)!=0)
				return Integer.compare(this.x, o.x);
			else
				return Integer.compare(this.y, o.y);
		}
		@Override
		public boolean equals(Object obj) 
		{
			Pair o = (Pair) obj;
			return this.x==o.x && this.y==o.y;
		}
		@Override
		public int hashCode() 
		{
			return Objects.hash(this.x,this.y);
		}
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