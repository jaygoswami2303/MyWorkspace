import java.io.*;
import java.util.*;
 
class Q4f {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int T = ir.nextInt();
		while(T>0) {
			int X11 = ir.nextInt();
			int Y11 = ir.nextInt();
			int X12 = ir.nextInt();
			int Y12 = ir.nextInt();
			int X21 = ir.nextInt();
			int Y21 = ir.nextInt();
			int X22 = ir.nextInt();
			int Y22 = ir.nextInt();
			if((X11==X12) && (X21==X22) && (X11==X21) && ((Y11>=Math.min(Y21,Y22) && Y11<=Math.max(Y21,Y22)) || (Y12>=Math.min(Y21,Y22) && Y12<=Math.max(Y21,Y22))))
				pw.println("yes");
			else if((X11==X12) && (Y21==Y22) && ((X11==X21 && Y11==Y21) || (X11==X22 && Y11==Y22) || (X12==X21 && Y12==Y21) || (X12==X22 && Y12==Y22)))
					pw.println("yes");
			else if((Y11==Y12) && (X21==X22) && ((X11==X21 && Y11==Y21) || (X11==X22 && Y11==Y22) || (X12==X21 && Y12==Y21) || (X12==X22 && Y12==Y22)))
				pw.println("yes");
			else if((Y11==Y12) && (Y21==Y22) && (Y11==Y21) && ((X11>=Math.min(X21,X22) && X11<=Math.max(X21,X22)) || (X12>=Math.min(X21,X22) && X12<=Math.max(X21,X22))))
				pw.println("yes");
			else
				pw.println("no");
			T--;
		}
		pw.close();
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