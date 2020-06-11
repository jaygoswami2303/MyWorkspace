import java.io.*;
import java.util.*;
 
public class Q6 {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		int m = ir.nextInt();
		int s[] = new int[n];
		int e[][] = new int[n][n];
		Graph g[] = new Graph[n];
		for(int i=0;i<n;i++)
			g[i] = new Graph();
		for(int i=0;i<n;i++) {
			int st = ir.nextInt()-1;
			g[st].addNode(i);
			s[i] = st;
		}
		for(int i=0;i<m;i++) {
			int u = ir.nextInt()-1;
			int v = ir.nextInt()-1;
			int w = ir.nextInt();
			if(s[u]==s[v]) {
				g[s[u]].addEdge(u, v, w);
				g[s[v]].addEdge(v, u, w);
			}
			else {
				if(e[s[u]][s[v]]==0)
					e[s[u]][s[v]] = w;
				else
					e[s[u]][s[v]] = Math.min(w,e[s[u]][s[v]]);
				if(e[s[v]][s[u]]==0)
					e[s[v]][s[u]] = w;
				else
					e[s[v]][s[u]] = Math.min(w,e[s[v]][s[u]]);
			}
		}
		long sum = 0;
		for(int i=0;i<n;i++) {
			if(g[i].nodes.size()!=0) {
				sum += g[i].MST();
			}
			if(i!=0)
				sum += e[i-1][i];
		}
		pw.println(sum);
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

class Graph {
	ArrayList<ArrayList<Pair>> ar;
	ArrayList<Integer> nodes;
	HashMap<Integer,Integer> hm;
	
	public Graph() {
		ar = new ArrayList<ArrayList<Pair>>();
		nodes = new ArrayList<Integer>();
		hm = new HashMap<>();
	}
	
	public void addNode(int a) {
		nodes.add(a);
		hm.put(a, nodes.size()-1);
		ar.add(new ArrayList<Pair>());
	}
	
	public void addEdge(int u,int v,int w) {
		ar.get(hm.get(u)).add(new Pair(v,w));
		ar.get(hm.get(v)).add(new Pair(u,w));
	}
	
	public int MST() {
		int n = nodes.size();
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair p1,Pair p2){
                if (p1.weight>p2.weight) {
                    return 1;
                }
                return -1;
            }
        });
		for (int i = 0; i < ar.get(0).size(); i++) {
            pq.add(ar.get(0).get(i));
        }
		int count = 0;
        boolean v[] = new boolean[n+1];
        v[0] = true;
        int k = 1;
        while(k<n&&pq.size()!=0){
            Pair temp = pq.remove();
            int u = temp.point;
            int weight = temp.weight;
            if (v[hm.get(u)]) {
                continue;
            }
            k++;
            count+=weight;
            v[hm.get(u)] = true;
            for (int i = 0; i < ar.get(hm.get(u)).size(); i++) {
                if (!v[hm.get(ar.get(hm.get(u)).get(i).point)]) {
                    pq.add(ar.get(hm.get(u)).get(i));
                }
            }
        }
        return count;
	}
}

class Pair{
    int point;
    int weight;
    Pair(int p,int w){
        this.point = p;
        this.weight = w;
    }
}