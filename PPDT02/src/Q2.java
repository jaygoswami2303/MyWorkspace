import java.io.*;
import java.util.*;
 
public class Q2 {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t = ir.nextInt();
		while(t-->0) {
			int n = ir.nextInt();
			int m = ir.nextInt();
			HashSet<Pair> hs = new HashSet<>();
			int a[][] = new int[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					a[i][j] = ir.nextInt();
					if(a[i][j]==1)
						hs.add(new Pair(i,j));
				}
			}
			int totCount = 0;
			int max = 0;
			int temp = 0;
			Queue<Pair> queue = new LinkedList<>();
			Pair p = new Pair(0, 0);
			if(!hs.isEmpty())
				p = hs.iterator().next();
			queue.add(p);
			hs.remove(p);
			temp++;
			a[p.x][p.y] = 0;
			while(!hs.isEmpty()) {
				if(queue.isEmpty()) {
					if(!hs.isEmpty()) {
						p = hs.iterator().next();
						queue.add(p);
						hs.remove(p);
						a[p.x][p.y] = 0;
						totCount++;
						if(temp>max)
							max = temp;
						temp = 1;
					}
				}
				else {
					p = queue.remove();
					int xstart = Math.max(p.x-1, 0);
					int xend = Math.min(p.x+1, n-1);
					int ystart = Math.max(p.y-1, 0);
					int yend = Math.min(p.y+1, m-1);
					for(int i=xstart;i<=xend;i++) {
						for(int j=ystart;j<=yend;j++) {
							if(a[i][j]==1) {
								p = new Pair(i,j);
								queue.add(p);
								hs.remove(p);
								a[i][j] = 0;
								temp++;
							}
						}
					}
				}
			}
			if(temp!=0) {
				totCount++;
				if(temp>max)
					max = temp;
			}
			pw.println(totCount + " " + max);
		}
		pw.close();
	}
	
	static long modulo(long a,long b,long c) {
		long x=1;
		long y=a;
		while(b > 0){
			if(b%2 == 1){
				x=(x*y)%c;
			}
			y = (y*y)%c; // squaring the base
			b /= 2;
		}
		return  x%c;
	}
	static class Pair implements Comparable<Pair>{
		int x;
		int y;

		Pair(int xx,int yy){
			x=xx;
			y=yy;
		}
		public String toString() {
			return "[x="+this.x+", y="+this.y+"]";
		}
		@Override
		public int compareTo(Pair o) {
			if(Integer.compare(this.x, o.x)!=0)
				return -Integer.compare(this.x, o.x);
			else
				return -Integer.compare(this.y, o.y);
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
	public static void debug(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
	
	public static long lcm(long a,long b) {
		return a*b/gcd(a,b);
	}
	
	public static long gcd(long a,long b) {
		if(a==0)
			return b;
		return gcd(b%a,a);
	}
	
	public static boolean isPrime(long a) {
		for(long i=2;i*i<=a;i++)
			if(a%i==0)
				return false;
		return true;
	}
	
	static class InputReader {
 
		private final InputStream stream;
		private final byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;
 
		public InputReader(InputStream stream) {
			this.stream = stream;
		}
 
		public int snext() {
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars) {
				curChar = 0;
				try {
					snumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}
 
		public int nextInt() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public long nextLong() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public int[] nextIntArray(int n) {
			int a[] = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
 
		public String readString() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}
 
		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
 
}