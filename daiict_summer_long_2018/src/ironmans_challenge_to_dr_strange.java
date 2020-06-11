import java.io.*;
import java.util.*;
 
public class ironmans_challenge_to_dr_strange {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		int q = ir.nextInt();
		int a[] = new int[n];
		for(int i=0;i<n;i++)
			a[i] = ir.nextInt();
		SegmentTree segmentTree = new SegmentTree(a, n);
		while(q-->0) {
			int l = ir.nextInt()-1;
			int r = ir.nextInt()-1;
			pw.println(segmentTree.getMax(l, r));
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
			return Integer.compare(this.x, o.x);
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
		int left[];
		int right[];
		int leftCount[];
		int rightCount[];
		int n;
		int a[];
		
		public SegmentTree(int a[], int n) {
			this.a = a;
			this.n = n;
			int height = (int) (Math.ceil(Math.log(n)/Math.log(2)));
			int size = 2*(int)(Math.pow(2,height)) - 1;
			tree = new int[size];
			left = new int[size];
			right = new int[size];
			leftCount = new int[size];
			rightCount = new int[size];
			build(0, n-1, 0);
		}
		
		public int build(int s, int e, int i) {
			if(s==e) {
				tree[i] = 1;
				left[i] = s;
				right[i] = s;
				leftCount[i] = 1;
				rightCount[i] = 1;
				return tree[i];
			}
			
			int mid = (s+e)/2;
			tree[i] = Math.max(build(s, mid, i*2+1), build(mid+1, e, i*2+2));
			if(a[right[i*2+1]]==-a[left[i*2+2]]) {
				if(rightCount[i*2+1]+leftCount[i*2+2]>tree[i])
					tree[i] = rightCount[i*2+1]+leftCount[i*2+2];
				left[i] = left[i*2+1];
				leftCount[i] = leftCount[i*2+1];
				if(right[i*2+1] - left[i*2+1] + 1 == leftCount[i*2+1])
					leftCount[i] += leftCount[i*2+2];
				right[i] = right[i*2+2];
				rightCount[i] = rightCount[i*2+2];
				if(right[i*2+2] - left[i*2+2] + 1 == rightCount[i*2+2])
					rightCount[i] += rightCount[i*2+1]; 
			}
			else {
				left[i] = left[i*2+1];
				leftCount[i] = leftCount[i*2+1];
				right[i] = right[i*2+2];
				rightCount[i] = rightCount[i*2+2];
			}
			return tree[i];
		}
		
		int getMax(int s, int e) {
			return getMaxUtil(0, n-1, s, e, 0);
		}
		
		int getMaxUtil(int s, int e, int qs, int qe, int i) {
			if(qs<=s && qe>=e)
				return tree[i];
			
			if(qs>e || qe<s)
				return 0;
				
			int mid = (s+e)/2;
			int ans = Math.max(getMaxUtil(s, mid, qs, qe, 2*i+1), getMaxUtil(mid+1, e, qs, qe, 2*i+2));
			if(a[right[i*2+1]]==-a[left[i*2+2]]) {
				int temp = -Math.max(right[i*2+1]+1-rightCount[i*2+1],qs) + Math.min(left[i*2+2]+leftCount[i*2+2]-1,qe) + 1;
				if(temp>ans)
					ans = temp;
			}
			return ans;
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