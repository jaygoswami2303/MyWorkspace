package q3;

import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[] args) {
		 InputReader ir = new InputReader(System.in);
		 PrintWriter pw = new PrintWriter(System.out);
		 int t = ir.nextInt();
		 while(t>0) {
			 int n = ir.nextInt();
			 HashSet<Integer> hs = new HashSet<>();
			 int count=0;
			 for(int i=0;i<n;i++) {
				 int temp = ir.nextInt();
				 hs.add(temp);
				 if(!(temp==0 || temp==1))
					 count++;
			 }
			 boolean flag = false;
			 int s = hs.size();
			 if(s<4) {
				 if(s==3) {
					 flag = hs.contains(0) && hs.contains(1) && count==1;
                     if(hs.contains(-1))
                    	 flag = hs.contains(0) && hs.contains(1) ;
				 }
				 else if(s==2) {
					 flag = (hs.contains(0) || hs.contains(1)) && count<2;
					 if(hs.contains(-1))
					 {
						 if(count>1)
							 flag = hs.contains(1);
						 else
							 flag = hs.contains(1) || hs.contains(0);
					 }
				 }
				 else {
					 flag = count<2;
				 }
			 }
			 if(flag) {
				 pw.println("yes");
			 }
			 else {
				 pw.println("no");
			 }
			 t--;
		 }
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
