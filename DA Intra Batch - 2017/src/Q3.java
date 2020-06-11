import java.io.*;
import java.util.*;
 
public class Q3 {
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t = ir.nextInt();
		while(t-->0) {
			int n = ir.nextInt();
			ArrayList<Integer> adj[] = new ArrayList[n];
			for(int i=0;i<n;i++)
				adj[i] = new ArrayList<Integer>();
			for(int i=0;i<n-1;i++) {
				int x = ir.nextInt()-1;
				int y = ir.nextInt()-1;
				adj[x].add(y);
				adj[y].add(x);
			}
			int level[] = new int[n];
		    boolean v[] = new boolean[n];
	        Queue<Integer> queue = new LinkedList<Integer>();
	        queue.add(0);
	        v[0] = true;
	        level[0] = 0;
	        int temp, current;
	        ArrayList<Integer> ar;
	        while(queue.size()!=0){
	        	temp = queue.poll();
	            ar = adj[temp];
	            for (int i = 0; i < ar.size(); i++) {
	            	current = ar.get(i); 
	                if (!v[current]) {
	                    queue.add(current);
	                    v[current] = true;
	                    level[current] = level[temp]+1;
	                }
	            }
	        }
	        int q = ir.nextInt();
	        while(q-->0) {
	        	int a = ir.nextInt()-1;
	        	int b = ir.nextInt()-1;
	        	int c = ir.nextInt()-1;
	        	if((level[a]%2)==(level[b]%2) && (level[b]%2)==(level[c]%2)) {
	        		Stack<Integer> stack = new Stack<Integer>();
	        		v = new boolean[n];
	        		stack.push(a);
	        		v[a] = true;
	        		HashSet<Integer> hs = new HashSet<Integer>();
	        		hs.add(a);
	        		boolean correct = true;
	        		while(!stack.isEmpty() && correct) {
	        			temp = stack.peek();
	        			hs.add(temp);
	        			ar = adj[temp];
	        			for(int i=0;i<ar.size();i++) {
	        				current = ar.get(i);
	        				if(!v[current]) {
	        					stack.push(current);
	        					v[current] = true;
	        				}
	        			}
	        			if(hs.contains(a)&&hs.contains(b)&&hs.contains(c))
	        				correct = false;
	        			if(stack.peek()==temp) {
	        				stack.pop();
	        				hs.remove(temp);
	        			}
	        		}
	        		if(correct)
	        			pw.println(1);
	        		else
	        			pw.println(0);
	        	}
	        	else
	        		pw.println(0);
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