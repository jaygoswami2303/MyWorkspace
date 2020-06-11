import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) {
		 InputReader ir = new InputReader(System.in);
		 PrintWriter pw = new PrintWriter(System.out);
		 ArrayList<Integer> A = new ArrayList<Integer>();
		 A.add(10000000);
		 ArrayList<Integer> B = new ArrayList<Integer>();
		 B.add(10000000);
		 ArrayList<Integer> ans = intersect(A,B);
		 for(int i=0;i<ans.size();i++)
			 pw.println(ans.get(i));
		 pw.close();
	}
	
	public static ArrayList<Integer> intersect(final List<Integer> A, final List<Integer> B) {
        int i = 0;
        int j = 0;
        ArrayList<Integer> common = new ArrayList<Integer>();
        while(i<A.size() && j<B.size()) {
            if(A.get(i).equals(B.get(j))) {
                common.add(A.get(i));
                i++;
                j++;
            }
            else if(A.get(i)>B.get(j))
                j++;
            else
                i++;
        }
        return common;
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