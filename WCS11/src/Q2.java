import java.io.*;
import java.util.*;
 
public class Q2 {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		String s = ir.readString();
		int k = ir.nextInt();
		int b = ir.nextInt();
		int m = ir.nextInt();
		int n = s.length();
		int pow[] = new int[n];
		int num[] = new int[n];
		pow[0] = 1;
		for(int i=1;i<n;i++)
			pow[i] = (pow[i-1]*b)%m;
		for(int i=0;i<n;i++)
			num[i] = (s.charAt(i)-'0')%m;
		int count = 0;
		for(int i=0;i<n-k+1;i++) {
			int temp=0;
			for(int j=k-1;j>=0;j--) {
				temp = (temp + (pow[k-1-j]*num[i+j])%m)%m;
			}	
			count = count + temp;
		}	
		pw.print(count);
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
		long x;
		int y;

		Pair(long xx,int yy){
			x=xx;
			y=yy;
		}
		public String toString() {
			return "[x="+this.x+", y="+this.y+"]";
		}
		@Override
		public int compareTo(Pair o) {
			if(Long.compare(this.x, o.x)!=0)
				return -Long.compare(this.x, o.x);
			else
				return -Long.compare(this.y, o.y);
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