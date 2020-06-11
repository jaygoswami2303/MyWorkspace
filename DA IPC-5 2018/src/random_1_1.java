import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
 
public class random_1_1 {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		Node nodes[] = new Node[n];
		for(int i=0;i<n;i++)
			nodes[i] = new Node(i);
		ArrayList<HashSet<Integer>> hs = new ArrayList<HashSet<Integer>>();
		for(int i=0;i<n;i++)
			hs.add(new HashSet<Integer>());
		for(int i=0;i<n-1;i++) {
			int u = ir.nextInt()-1;
			int v = ir.nextInt()-1;
			hs.get(u).add(v);
			hs.get(v).add(u);
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		while(!queue.isEmpty()) {
			int top = queue.remove();
			Iterator<Integer> it = hs.get(top).iterator();
			while(it.hasNext()) {
				int current = it.next();
				nodes[top].next.add(nodes[current]);
				hs.get(current).remove(top);
				queue.add(current);
			}
		}
		expectedScore(nodes[0]);
		double avg = 0;
		for(int i=0;i<n;i++)
			avg += nodes[i].score;
		avg /= n;
		avg = BigDecimal.valueOf(avg).setScale(4, RoundingMode.HALF_UP).doubleValue();
		pw.println(String.format("%.4f", avg));
		pw.close();
	}
	
	static double expectedScore(Node u) {
		if(u.next.isEmpty()) {
			u.score = 0;
			u.count = 1;
			return 0;
		}
		ArrayList<Node> next = u.next;
		double sum = 0;
		int num = 0;
		for(int i=0;i<next.size();i++) {
			double temp2 = expectedScore(next.get(i))+1; 
			int temp = next.get(i).count;
			sum += temp2*temp;
			num += temp;
		}
		sum /= num;
		u.score = sum;
		u.count = num;
		return sum;
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
}

class Node {
	int value;
	double score;
	ArrayList<Node> next;
	int count;
	
	public Node(int value) {
		this.value = value;
		this.score = 0;
		this.next = new ArrayList<Node>();
	}
}