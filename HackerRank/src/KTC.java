import java.io.*;
import java.util.*;
 
public class KTC {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
	  	int N = sc.nextInt();
	  	sc.nextLine();
        while(N-->0) {
            String keyWord = sc.nextLine();
            String cipherText = sc.nextLine();
            HashSet<Character> hs = new HashSet<Character>();
            TreeSet<Character> ts = new TreeSet<Character>();
            String temp = "";
            for(int i=0;i<keyWord.length();i++) {
            	if(!hs.contains(keyWord.charAt(i)))
            		temp += keyWord.charAt(i);
                hs.add(keyWord.charAt(i));
                ts.add(keyWord.charAt(i));
            }
            String ar[] = new String[26/temp.length()+1];
            ar[0] = new String(temp);
            char a = 'A';
            for(int i=1;a<='Z';i++) {
                temp = "";
                for(int j=0;a<='Z' && j<ar[0].length();j++) {
                    if(!hs.contains(a)) {
                        temp += a;
                    }
                    else {
                        j--;
                    }
                    a++;
                }
                ar[i] = new String(temp);
            }
            for(int i=0;i<ar.length;i++)
            	pw.println(ar[i]);
            pw.flush();
            char change[] = new char[26];
            Iterator<Character> it = ts.iterator();
            a = 'A';
            while(it.hasNext()) {
            	int index = ar[0].indexOf(it.next());
                for(int i=0;i<ar.length;i++)
                	if(ar[i]!=null && index<ar[i].length()) {
                		change[ar[i].charAt(index)-'A'] = a;
                		a++;
                	}
            }
            String original = "";
            for(int j=0;j<cipherText.length();j++) {
            	if(cipherText.charAt(j)==' ')
            		original += ' ';
            	else
            		original += change[cipherText.charAt(j)-'A'];
            }
            pw.println(original);
        }
        sc.close();
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