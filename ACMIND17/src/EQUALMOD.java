import java.io.*;
import java.util.*;
 
public class EQUALMOD
{
	
	public static void main(String[] args)
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t = ir.nextInt();
		while(t-->0) {
			int n = ir.nextInt();
			long a[] = new long[n];
			long b[] = new long[n];
			long sum = 0;
			long min = 1000000000;
			for(int i=0;i<n;i++) {
				a[i] = ir.nextLong();
				sum += a[i];
			}
			for(int i=0;i<n;i++) {
				b[i] = ir.nextLong();
				if(b[i]<min)
					min = b[i];
			}
			ArrayList<Pair> ar = new ArrayList<Pair>();
			for(int i=0;i<n;i++)
				ar.add(new Pair(a[i],b[i]));
			Collections.sort(ar);
			long bSum[] = new long[n+1];
			bSum[n] = -sum;
			for(int i=n-1;i>=0;i--) {
				bSum[i] = bSum[i+1] + ar.get(i).y;
			}
			min--;
			int l=0,r=n-1;
			int mid = l+((r-l)/2);
			while(l<=r) {
				mid = (l+r)/2;
				if(ar.get(mid).x == min) {
					if(mid<n-1 && ar.get(mid+1).x == min) {
						l = mid+1;
					}
					else {
						break;
					}
				}
				else if(ar.get(mid).x>min) {
					r = mid-1;
				}
				else {
					l = mid+1;
				}
			}
			long x = ar.get(mid).x;
			long ans = n*x + bSum[mid+1];
			pw.println(ans);
		}
		pw.close();
	}
	
	static class Pair implements Comparable<Pair>{
		long x;
		long y;
 
		Pair(long xx,long yy){
			x=xx;
			y=yy;
		}
		public String toString() {
			return "[x="+this.x+", y="+this.y+"]";
		}
		@Override
		public int compareTo(Pair o) {
			if(Long.compare(this.x, o.x)!=0)
				return Long.compare(this.x, o.x);
			else
				return Long.compare(this.y, o.y);
		}
		@Override
		public boolean equals(Object obj) {
			Pair o = (Pair) obj;
			return this.x==o.x && this.y==o.y;
		}
		@Override
		public int hashCode() {
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