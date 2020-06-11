import java.io.*;
import java.util.*;
 
public class Q4 {
	
	public static void main(String[] args) {
		 InputReader ir = new InputReader(System.in);
		 PrintWriter pw = new PrintWriter(System.out);
		 boolean check[] = new boolean[1000002];
		 boolean done[] = new boolean[1000002];
		 check[0] = false;
		 done[0] = true;
		 /*for(int i=1;i<1000002;i++) {
			 check[i] = isPrime(i);
		 }*/
		 int N = ir.nextInt();
		 int a[] = new int[N+1];
		 int Q = ir.nextInt();
		 int countGood[] = new int[N+1];
		 a[0] = 0;
		 countGood[0] = 0;
		 for(int i=1;i<N+1;i++) {
			 a[i] = ir.nextInt();
			 countGood[i] = countGood[i-1];
			 int f = a[i];
			 if(!done[f]) {
				 check[f] = isPrime(f);
				 done[f] = true;
			 }
			 if(!done[f+1]) {
				 check[f+1] = isPrime(f+1);
				 done[f+1] = true;
			 }
			 if(check[f]==check[f+1])
				 countGood[i]++;
		 }
		 
		 while(Q>0) {
			 int x = ir.nextInt();
			 int y = ir.nextInt();
			 int z = ir.nextInt();
			 if(x==1) {
				 int f = a[y];
				 if(!done[f]) {
					 check[f] = isPrime(f);
					 done[f] = true;
				 }
				 if(!done[f+1]) {
					 check[f+1] = isPrime(f+1);
					 done[f+1] = true;
				 }
				 boolean prev = (check[f]==check[f+1]);
				 a[y] = z;
				 if(!done[z]) {
					 check[z] = isPrime(z);
					 done[z] = true;
				 }
				 if(!done[z+1]) {
					 check[z+1] = isPrime(z+1);
					 done[z+1] = true;
				 }
				 if(prev!=(check[z]==check[z+1])) {
					 int j;
					 if(prev) {
						 j = -1;
					 }
					 else {
						 j = 1;
					 }
					 for(int i=y;i<N+1;i++)
						 countGood[i] = countGood[i] + j;
				 }
			 }
			 else {
				 int count = countGood[z] - countGood[y-1];
				 /*int count = 0;
				 for(int i=y;i<=z;i++)
					 if(check[a[i]]==check[a[i]+1])
						 count++;*/
				 pw.println(count);
			 }
			 Q--;
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
	
	public static boolean isMyPrime(long a) {
		boolean agone = false;
		boolean bgone = false;
		for(long i=2;i*i<=(a+1);i++) {
			if(a%i==0) {
				if(bgone)
					return true;
				else
					agone = true;
			}
			if((a+1)%i==0) {
				if(agone) {
					return true;
				}
				else {
					bgone = true;
				}
			}
		}
		if(agone || bgone)
			return false;
		else
			return true;
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