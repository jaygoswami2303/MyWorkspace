import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) {
		 InputReader ir = new InputReader(System.in);
		 PrintWriter pw = new PrintWriter(System.out);
		 int N = ir.nextInt();
		 TreeSet<Number> ts = new TreeSet<Number>();
		 for(int i=0;i<N;i++) {
			 int temp = ir.nextInt();
			 Number n = new Number(temp);
			 ts.add(n);
		 }
		 String ans = "";
		 while(!ts.isEmpty()) {
			 ans = ans + ts.last().t;
			 ts.remove(ts.last());
		 }
		 pw.print(ans);
		 pw.close();
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

class Number implements Comparable{
	int n;
	int s;
	String t;
	
	public Number(int n) {
		this.n = n;
		this.s = n;
		t = Integer.toString(this.n);
		int l = t.length();
		if(l<7)
		{
			for(int i=0;i<6-l;i++)
				s = s*10;
		}
	}
	
	public int getN() {
		return this.n;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Number a = (Number)arg0;
		return Integer.compare(this.s,a.s);
	}
	
	
}