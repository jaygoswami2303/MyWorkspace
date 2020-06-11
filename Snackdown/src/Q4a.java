import java.io.*;
import java.util.*;
 
public class Q4a {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int T = ir.nextInt();
		while(T>0) {
			int X[][] = new int[2][2];
			int Y[][] = new int[2][2];
			for(int i=0;i<2;i++) {
				for(int j=0;j<2;j++) {
					X[i][j] = ir.nextInt();
					Y[i][j] = ir.nextInt();
				}
			}
			boolean snakeX[] = new boolean[2];
			for(int i=0;i<2;i++) {
				if(Y[i][0]==Y[i][1])
					snakeX[i] = true;
			}
			for(int i=0;i<2;i++) {
				if(snakeX[i]) {
					if(X[i][0]>X[i][1]) {
						int temp = X[i][0];
						X[i][0] = X[i][1];
						X[i][1] = temp;
					}
				}
				else {
					if(Y[i][0]>Y[i][1]) {
						int temp = Y[i][0];
						Y[i][0] = Y[i][1];
						Y[i][1] = temp;
					}
				}
			}
			boolean same = false;
			if(snakeX[0]^snakeX[1]) {
					if(Y[0][0]==Y[1][0] && X[0][0]==X[1][0])
						same = true;
					else if(Y[0][0]==Y[1][1] && X[0][0]==X[1][1])
						same = true;
					else if(Y[0][1]==Y[1][0] && X[0][1]==X[1][0])
						same = true;
					else if(Y[0][1]==Y[1][1] && X[0][1]==X[1][1])
						same = true;
			}
			else {
				if(snakeX[0]) {
					if(Y[0][0]==Y[1][0]) {
						if(X[0][0]>=X[1][0]) {
							if(X[0][0]<=X[1][1])
								same = true;
						}
						else {
							if(X[1][0]<=X[0][1])
								same = true;
						}
					}
				}
				else {
					if(X[0][0]==X[1][0]) {
						if(Y[0][0]>=Y[1][0]) {
							if(Y[0][0]<=Y[1][1])
								same = true;
						}
						else {
							if(Y[1][0]<=Y[0][1])
								same = true;
						}
					}
				}
			}
			if(same) {
				pw.println("yes");
			}
			else {
				pw.println("no");
			}
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