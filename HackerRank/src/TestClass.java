import java.io.*;
import java.util.*;

public class TestClass {
	
	public static void main(String[] args) {
		 InputReader ir = new InputReader(System.in);
		 PrintWriter pw = new PrintWriter(System.out);
		 int t = ir.nextInt();
		 while(t>0) {
			 String s = ir.readString();
			 int max = getLongest(s);
			 if(max<2)
				 max = -1;
			 pw.println(max);
			 t--;
		 }
		 pw.close();
	}
	
	public static int getLongest(String s) {
		int k,l=s.length();
		for(k=0;k<l-1;k++) {
			if(s.charAt(k)==s.charAt(k+1))
				break;
		}
		if(k==l-1) {
			if(l>2) {
			for(k=0;k<l-2;k++) {
				if(s.charAt(k)==s.charAt(k+2))
					break;
			}
			if(k==l-2)
				return l;
			else
				return Math.max(getLongest(s.substring(0,k+2)),getLongest(s.substring(k+2)));
			}
			else
				return l;
		}
		else{
			if(l>1)
				return Math.max(getLongest(s.substring(0,k+1)),getLongest(s.substring(k+1)));
			else
				return l;
		}
	}
	
	public static boolean function(String str){

        for(int i=0;i<str.length();i++){
            for(int j=i+1;j<str.length();j++){
                String sub=str.substring(i, j+1); 
                boolean flag=true;
                for(int k=0;k<sub.length();k++){
                    if(sub.charAt(k)!=sub.charAt(sub.length()-k-1)){
                        flag=false;
                    }
                }
                if(flag) return false;
            }
        }
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