import java.io.*;
import java.util.*;
 
public class fact_of_factorial_4 {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		long pow[] = new long[121];
		for(int i=2;i<=n;i++) {
			int temp = i;
			for(int j=2;j*j<=temp;j++) {
				while(temp%j==0) {
					temp /= j;
					pow[j]++;
				}
			}
			if(temp!=1)
				pow[temp]++;
		}
		long ans = 1;
		for(int i=2;i<121;i++)
			ans *= (pow[i]+1);
		pw.print(ans);
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
 
	static class SegmentTree {
		int tree[];
		int n;
		int a[];
		
		public SegmentTree(int a[], int n) {
			this.a = a;
			this.n = n;
			int height = (int) (Math.ceil(Math.log(n)/Math.log(2)));
			int size = 2*(int)(Math.pow(2,height)) - 1;
			tree = new int[size];
			build(0, n-1, 0);
		}
		
		public int build(int s, int e, int i) {
			if(s==e) {
				tree[i] = a[s];
				return a[s];
			}
			
			int mid = (s+e)/2;
			tree[i] = build(s, mid, i*2+1) + build(mid+1, e, i*2+2);
			return tree[i];
		}
		
		int getSum(int s, int e) {
			return getSumUtil(0, n-1, s, e, 0);
		}
		
		int getSumUtil(int s, int e, int qs, int qe, int i) {
			if(qs<=s && qe>=e)
				return tree[i];
			
			if(qs>e || qe<s)
				return 0;
				
			int mid = (s+e)/2;
			return getSumUtil(s, mid, qs, qe, 2*i+1) + getSumUtil(mid+1, e, qs, qe, 2*i+2);
		}
		
		void updateValue(int i, int v) {
			int diff = v - a[i];
			
			a[i] = v;
			
			updateValueUtil(0, n-1, i, diff, 0);
		}
		
		void updateValueUtil(int s, int e, int ui, int diff, int i) {
			if(ui<s || ui>e)
				return;
			tree[i] = tree[i] + diff;
			if(s!=e) {
				int mid = (s+e)/2;
				updateValueUtil(s, mid, ui, diff, 2*i+1);
				updateValueUtil(mid+1, e, ui, diff, 2*i+2);
			}
		}
	}
}