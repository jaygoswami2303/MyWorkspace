import java.io.*;
import java.util.*;
 
public class Q3 {
	
	public static void main(String[] args) {
		 InputReader ir = new InputReader(System.in);
		 PrintWriter pw = new PrintWriter(System.out);
		 
		 int N = ir.nextInt();
		 int Q = ir.nextInt();
		 int a[] = new int[N+1];
		 a[0] = 0;
		 for(int i=1;i<=N;i++)
			 a[i] = ir.nextInt();
		 SegmentTree sT = new SegmentTree(a);
		 sT.printTree();
		 while(Q>0) {
			 int c = ir.nextInt();
			 int x = ir.nextInt();
			 int y = ir.nextInt();
			 if(c==1) {
				 sT.update();
			 }
			 else if(c==2) {
				 int ans = sT.query();
				 pw.println(ans);
			 }
			 Q--;
		 }
		 pw.close();
	}
	
	static class SegmentTree {
		int n;
		int a[];
		int tree[];
		
		public SegmentTree(int ar[]) {
			n = ar.length;
			a = ar;
			tree = new int[2*(n-1)];
			build(1,1,n);
		}
		
		public void build(int node,int start,int end) {
			if(start==end) {
				tree[node] = a[start];
			}
			else {
				int mid = (start+end)/2;
				
				build(2*node,start,mid);
				
				build(2*node+1,mid+1,end);
				
				tree[node] = tree[2*node] + tree[2*node+1];
			}
		}
		
		public void update() {
			
		}
		
		public int query() {
			int ans = 0;
			
			return ans;
		}
		
		public void printTree() {
			PrintWriter pw = new PrintWriter(System.out);
			for(int i=0;i<2*n-1;i++)
				pw.print(tree[i] + " ");
			pw.close();
		}
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