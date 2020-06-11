import java.io.*;
import java.util.*;
 
public class SHKSTR {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		String s[] = new String[n];
		for(int i=0;i<n;i++)
			s[i] = ir.readString();
		Trie trie = new Trie(n);
		for(int i=0;i<n;i++) {
			String str = s[i];
			Trie temp = trie;
			for(int j=0;j<str.length();j++) {
				int c = str.charAt(j)-'a';
				if(temp.next[c]==null) {
					temp.next[c] = new Trie(n);
				}
				temp = temp.next[c];
				if(temp.ar[i]==null) {
					if(i>0 && temp.ar[i-1]!=null && temp.ar[i-1].compareTo(str)<0) {
						temp.ar[i] = temp.ar[i-1];
					}
					else
						temp.ar[i] = str;
				}
				else if(temp.ar[i].compareTo(str)>0)
					temp.ar[i] = str;
			}
			//temp.index.add(i);
		}
		int q = ir.nextInt();
		while(q-->0) {
			int r = ir.nextInt()-1;
			String p = ir.readString();
			Trie temp = trie;
			String ans = "";
			for(int i=0;i<p.length();i++) {
				int c = p.charAt(i)-'a';
				temp = temp.next[c];
				if(temp==null)
					break;
				String str = temp.ar[r];
				for(int j=r-1;j>=0;j--) {
					if(str==null || (temp.ar[j]!=null && temp.ar[j].compareTo(str)<0))
						str = temp.ar[j];
				}
				if(str==null)
					break;
				ans = str;
			}
			pw.println(ans);
		}
		pw.close();
	}
	
	static void sort(Trie trie) {
		Collections.sort(trie.index);
		for(int i=0;i<26;i++) {
			if(trie.next[i]!=null)
				sort(trie.next[i]);
		}
	}
	
	static class Trie {
		Trie next[];
		ArrayList<Integer> index;
		String ar[];
		
		public Trie(int n) {
			next = new Trie[26];
			index = new ArrayList<Integer>();
			ar = new String[n];
		}
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