import java.io.*;
import java.util.*;
 
public class TYTACTIC
{
	
	public static void main(String[] args)
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		int m = ir.nextInt();
		int s[] = new int[n];
		for(int i=0;i<n;i++)
			s[i] = ir.nextInt();
		int g[][] = new int[n][n];
		for(int i=0;i<n-1;i++) {
			int a = ir.nextInt()-1;
			int b = ir.nextInt()-1;
			g[a][b] = 1;
		}
		Node tree[] = getArray(g);
		int ans[] = new int[n];
		int trans[] = new int[n];
	    for(int i=0;i<n;i++) {
	    	 ans[i] = tree[i].data;
	    	 trans[ans[i]] = i;
	    }
	    for(int i=0;i<n;i++)
	    	System.out.print(ans[i]+" ");
	    System.out.println();
	    for(int i=0;i<n;i++)
	    	System.out.print(trans[i]+" ");
	    System.out.println();
		pw.close();
	}
	
	static Node[] getArray(int g[][])
	{
		int n = g.length;
	    int parent[] = new int[n];
	 
	    Node root = new Node(0);
	 
	    TreeSet<Pair> ts = new TreeSet<Pair>();
	 
	    for (int i = 0; i < n; i++)
	    {
	        int sum = 0;
	        for (int j = 0; j < n; j++)
	            sum += g[i][j];
	        ts.add(new Pair(sum, i));
	    }
	 
	    Node node[] = new Node[n];
	    Iterator<Pair> it = ts.iterator();
	    while(it.hasNext())
	    {
	    	Pair p = it.next();
	    	int first = p.x;
	    	int second = p.y;
	    	node[second] = new Node(second);
	    	root = node[second];
	 
	    	if (first != 0)
	    	{
	    		for (int i = 0; i < n; i++)
	    		{
	    			if (parent[i]==0 && g[second][i]!=0)
	    			{
	    				if (node[second].left==null)
	    					node[second].left = node[i];
	    				else
	    					node[second].right = node[i];
	    				parent[i] = 1;
	    			}
	    		}
	    	}
	    }
	    Node tree[] = new Node[n];
	    tree[0] = root;
	    for(int i=1;i<n;i++) {
	    	int p = (i+1)/2 -1;
	    	if(2*p+1==i)
	    		tree[i] = tree[p].left;
	    	else
	    		tree[i] = tree[p].right;
		    System.out.println(i + " " + tree[i].data);
	    }
	    return tree;
	}
		
	static class InputReader
	{
 
		private final InputStream stream;
		private final byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;
 
		public InputReader(InputStream stream)
		{
			this.stream = stream;
		}
 
		public int snext()
		{
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars)
			{
				curChar = 0;
				try
				{
					snumChars = stream.read(buf);
				}
				catch (IOException e)
				{
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}
 
		public int nextInt()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public long nextLong()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public int[] nextIntArray(int n)
		{
			int a[] = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
 
		public String readString()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do
			{
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}
 
		public boolean isSpaceChar(int c)
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
		public interface SpaceCharFilter
		{
			public boolean isSpaceChar(int ch);
		}
	}
 
}

class SegmentTree 
{
	int tree[];
	
	SegmentTree(int arr[], int n)
	{
		int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
		int max_size = 2 * (int) Math.pow(2, x) - 1;

		tree = new int[max_size];

		constructSTUtil(arr, 0, n - 1, 0);
	}

	int getMid(int s, int e) {
		return s + (e - s) / 2;
	}

	int getSumUtil(int ss, int se, int qs, int qe, int si)
	{
	     if (qs <= ss && qe >= se)
	         return tree[si];

	     if (se < qs || ss > qe)
	         return 0;
	
	     int mid = getMid(ss, se);
	     return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
	             getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
	 }

	void updateValueUtil(int ss, int se, int i, int diff, int si)
	 {
	     if (i < ss || i > se)
	         return;
	
	     tree[si] = tree[si] + diff;
	     if (se != ss) {
	         int mid = getMid(ss, se);
	         updateValueUtil(ss, mid, i, diff, 2 * si + 1);
	         updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
	     }
	 }
	
	 void updateValue(int arr[], int n, int i, int new_val)
	 {
	     if (i < 0 || i > n - 1) {
	         return;
	     }
	
	     int diff = new_val - arr[i];
	
	     arr[i] = new_val;
	
	     updateValueUtil(0, n - 1, i, diff, 0);
	 }
	
	 int getSum(int n, int qs, int qe)
	 {
	     if (qs < 0 || qe > n - 1 || qs > qe) {
	         return -1;
	     }
	     return getSumUtil(0, n - 1, qs, qe, 0);
	 }
	
	 int constructSTUtil(int arr[], int ss, int se, int si)
	 {
	     if (ss == se) {
	         tree[si] = arr[ss];
	         return arr[ss];
	     }
	
	     int mid = getMid(ss, se);
	     tree[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
	              constructSTUtil(arr, mid + 1, se, si * 2 + 2);
	     return tree[si];
	 }
}

class Node
{
    int data;
    Node left, right;
    
    Node(int data) {
    	this.data = data;
    }
}

class Pair implements Comparable<Pair>{
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