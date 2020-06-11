import java.io.*;
import java.util.*;
 
public class COP {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		long p[] = new long[n];
		ArrayList<Pair> arP = new ArrayList<>();
		for(int j=0;j<n;j++) {
			p[j] = ir.nextLong();
			arP.add(new Pair(p[j],j));
		}
		long i[] = new long[n];
		ArrayList<Pair> arI = new ArrayList<>();
		for(int j=0;j<n;j++) {
			i[j] = ir.nextLong();
			arI.add(new Pair(i[j],j));
		}
		long s[] = new long[n];
		ArrayList<Pair> arS = new ArrayList<>();
		for(int j=0;j<n;j++) {
			s[j] = ir.nextLong();
			arS.add(new Pair(s[j],j));
		}
		Collections.sort(arP);
		Collections.sort(arI);
		Collections.sort(arS);
		int count = 0;
		for(int j=0;j<n;j++) {
			int keyP = 0;
			int start = 0;
			int end = arP.size()-1;
			while(start<=end) {
				keyP = (start+end)/2;
				if(arP.get(keyP).x == p[j]) {
					if(keyP<n-1 && arP.get(keyP+1).x == p[j]) {
						start = keyP+1;
					}
					else {
						break;
					}
				}
				else if(arP.get(keyP).x>p[j]) {
					end = keyP-1;
				}
				else {
					start = keyP+1;
				}
			}
			int keyI = 0;
			start = 0;
			end = arI.size()-1;
			while(start<=end) {
				keyI = (start+end)/2;
				if(arI.get(keyI).x == i[j]) {
					if(keyI<n-1 && arI.get(keyI+1).x == i[j]) {
						start = keyI+1;
					}
					else {
						break;
					}
				}
				else if(arI.get(keyI).x>i[j]) {
					end = keyI-1;
				}
				else {
					start = keyI+1;
				}
			}
			int keyS = 0;
			start = 0;
			end = arI.size()-1;
			while(start<=end) {
				keyS = (start+end)/2;
				if(arS.get(keyS).x == s[j]) {
					if(keyS<n-1 && arS.get(keyS+1).x == s[j]) {
						start = keyS+1;
					}
					else {
						break;
					}
				}
				else if(arS.get(keyS).x>s[j]) {
					end = keyS-1;
				}
				else {
					start = keyS+1;
				}
			}
			if(keyP>=keyS && keyP>=keyI) {
				for(int k=keyP+1;k<n;k++) {
					int key = arP.get(k).y;
					if(s[key]>s[j] && i[key]>i[j]) {
						count++;
						break;
					}
				}
			}
			else if(keyS>=keyP && keyS>=keyI) {
				for(int k=keyS+1;k<n;k++) {
					int key = arS.get(k).y;
					if(p[key]>p[j] && i[key]>i[j]) {
						count++;
						break;
					}
				}
			}
			else {
				for(int k=keyI+1;k<n;k++) {
					int key = arI.get(k).y;
					if(s[key]>s[j] && p[key]>p[j]) {
						count++;
						break;
					}
				}
			}
			
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
				return Long.compare(this.x, o.x);
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