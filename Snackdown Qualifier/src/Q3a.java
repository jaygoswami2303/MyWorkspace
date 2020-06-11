import java.io.*;
import java.util.*;
 
public class Q3a {
	
	public static void main(String[] args) {
		 InputReader ir = new InputReader(System.in);
		 PrintWriter pw = new PrintWriter(System.out);
		 int t = ir.nextInt();
		 while(t>0) {
			 int n = ir.nextInt();
			 int q = ir.nextInt();
			 long a[] = new long[n];
			 for(int i=0;i<n;i++) {
				 a[i] = ir.nextLong();
			 }
			 Arrays.sort(a);
			 long sum[] = new long[n+1];
			 sum[n] = 0;
			 for(int i=n-1;i>=0;i--) {
				 sum[i] = sum[i+1] + a[i];
			 }
			 while(q>0) {
				 long k = ir.nextLong();
				 int lowerBound = Arrays.binarySearch(a, k);
				 int first = 0;
				 int last = lowerBound-1;
				 int ans = n - lowerBound;
				 while(first<=last) {
					 int mid = (first+last)/2;
					 long diff = sum[mid] - sum[lowerBound-1];
					 int nums = lowerBound-1-mid;
					 long check = a[lowerBound-1]*nums - diff + (nums+1)*(k-a[lowerBound-1]);
					 if(mid==check) {
						 ans = n - mid;
						 break;
					 }
					 else if(mid>check) {
						 last = mid-1;
						 ans = n - mid;
					 }
					 else {
						 first = mid+1;
					 }
				 }
				 pw.println(ans);
				 q--;
			 }			 
			 t--;
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