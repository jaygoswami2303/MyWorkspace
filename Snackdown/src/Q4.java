import java.io.*;
import java.util.*;
 
public class Q4 {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int T = ir.nextInt();
		while(T>0) {
			int X[][] = new int[2][2];
			int Y[][] = new int[2][2];
			for(int i=0;i<2;i++) {
				for(int j=0;j<2;j++) {
					X[i][j] = ir.nextInt();
					Y[i][j] = ir.nextInt();
				}
			}
			boolean snakeX[] = new boolean[2];
			for(int i=0;i<2;i++) {
				if(Y[i][0]==Y[i][1])
					snakeX[i] = true;
			}
			ArrayList<HashSet<Vertex>> edge = new ArrayList<HashSet<Vertex>>();
			edge.add(new HashSet<Vertex>());
			edge.add(new HashSet<Vertex>());
			for(int i=0;i<2;i++) {
				if(snakeX[i]) {
					if(X[i][0]<X[i][1]) {
						for(int j=X[i][0];j<=X[i][1];j++) {
							Vertex v = new Vertex(j,Y[i][1]);
							edge.get(i).add(v);
						}
					}
					else {
						for(int j=X[i][1];j<=X[i][0];j++) {
							Vertex v = new Vertex(j,Y[i][1]);
							edge.get(i).add(v);
						}
					}
				}
				else {
					if(Y[i][0]<Y[i][1]) {
						for(int j=Y[i][0];j<=Y[i][1];j++) {
							Vertex v = new Vertex(X[i][1],j);
							edge.get(i).add(v);
						}
					}
					else {
						for(int j=Y[i][1];j<=Y[i][0];j++) {
							Vertex v = new Vertex(X[i][1],j);
							edge.get(i).add(v);
						}
					}
				}
			}
			boolean same = false;
			if(!(snakeX[0]^snakeX[1])) {
				Iterator<Vertex> i = edge.get(0).iterator();
				while(i.hasNext()) {
					Vertex v = i.next();
					if(edge.get(1).contains(v)) {
						same = true;
						break;
					}
				}
			}
			else {
				Iterator<Vertex> i = edge.get(0).iterator();
				while(i.hasNext()) {
					Vertex v = i.next();
					if(edge.get(1).contains(v)) {
						if((v.equals(new Vertex(X[0][0],Y[0][0])) || v.equals(new Vertex(X[0][1],Y[0][1]))) && (v.equals(new Vertex(X[1][0],Y[1][0])) || v.equals(new Vertex(X[1][1],Y[1][1]))))
							same = true;
						break;
					}
				}
			}
			if(same) {
				pw.println("yes");
			}
			else {
				pw.println("no");
			}
			T--;
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

class Vertex
{
	private int x;
	private int y;
	
	public Vertex(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void print()
	{
		System.out.println("x: " + x + " y: " + y);
	}
	
	public boolean equals(Object obj)
	{
		Vertex v = (Vertex)obj;
		return this.x == v.getX() && this.y == v.getY();
	}
	
    public int hashCode() {
        return new Integer(x).hashCode() * 31 + new Integer(y).hashCode();
    }
}