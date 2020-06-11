import java.io.*;
import java.util.*;
 
public class CPLAY {
	
	public static void main(String[] args) throws IOException {
		//InputReader ir = new InputReader(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		String s;
		while((s=br.readLine())!=null) {
		//while(!((s=ir.readString()).equals("\n"))) {
			if(s.length()!=20)
				break;
			int a = 0;
			int b = 0;
			int i;
			boolean aWins = true;
			boolean tie = false;
			for(i=0;i<10;i++) {
				if(i%2==0 && s.charAt(i)=='1')
					a++;
				else if(i%2!=0 && s.charAt(i)=='1')
					b++;
				int tempa = (10-i-1)/2;
				int tempb = tempa;
				if(i%2==0)
					tempb++;
				if(b>a+tempa) {
					aWins = false;
					break;
				}
				else if(a>b+tempb)
					break;
				//System.out.println(i + " " + a + " " + b + " " + tempa + " " + tempb);
			}
			int total = i+1;
			if(i==10) {
				total--;
				if(b>a)
					aWins = false;
				else if(b==a) {
					for(i=10;i<20;i++) {
						if(i%2==0 && s.charAt(i)=='1')
							a++;
						else if(i%2!=0 && s.charAt(i)=='1')
							b++;
						if(i%2!=0 && a!=b) {
							if(b>a)
								aWins = false;
							total = i+1;
							break;
						}
					}
					if(i==20)
						tie = true;					
				}
			}
			if(tie)
				pw.println("TIE");
			else {
				pw.print("TEAM-");
				if(aWins)
					pw.print("A");
				else
					pw.print("B");
				pw.println(" "+total);
			}
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