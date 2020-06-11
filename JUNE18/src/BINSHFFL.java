import java.io.*;
import java.util.*;
 
public class BINSHFFL {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t = ir.nextInt();
		while(t-->0) {
			long a = ir.nextLong();
			long b = ir.nextLong();
			int ans;
			if(a==b)
				ans = 0;
			else if(b==1 && a==0)
				ans = 1;
			else if(b==1 || b==0)
				ans = -1;
			else {
				int countA = 0;
				long temp = a;
				while(temp!=0) {
					if((temp&1)!=0)
						countA++;
					temp>>=1;
				}
				int countB = 0;
				int right = -1;
				int i = 1;
				temp = b;
				while(temp!=0) {
					if((temp&1)!=0) {
						if(right==-1)
							right = i;
						countB++;
					}
					i++;
					temp>>=1;
				}
				if(countA>countB) {
					int diff = countA-countB;
					if(right==diff+2)
						ans = 1;
					else if(right<diff+2) {
						ans = 2;
					}
					else {
						ans = right - diff -1;
					}
				}
				else if(countA<countB) {
					int diff = countB-countA;
					ans = diff;
					if(right!=1) {
						ans += right-1;
					}
				}
				else {
					if(right==1)
						ans = 2;
					else
						ans = right-1;
				}
			}
			pw.println(ans);
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
				return Integer.compare(this.x, o.x);
			else
				return Integer.compare(this.y, o.y);
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
	
	static long pow(long n,long p) {
		long  result = 1;
		if(p==0)
			return 1;
	          
		while(p!=0) {
			if(p%2==1)
				result *= n;	    
			p >>=1;
			n*=n;	    
		}
		return result;
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