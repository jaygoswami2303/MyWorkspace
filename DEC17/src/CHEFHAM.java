import java.io.*;
import java.util.*;
 
public class CHEFHAM {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t = ir.nextInt();
		while(t-->0) {
			int n = ir.nextInt();
			int a[] = new int[n];
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			ArrayList<Pair> ar = new ArrayList<Pair>();
			HashSet<Integer> hs = new HashSet<Integer>();
			for(int i=0;i<n;i++) {
				a[i] = ir.nextInt();
				if(hm.containsKey(a[i])) {
					ar.add(new Pair(hm.get(a[i]), i));
					hs.remove(hm.get(a[i]));
					hm.remove(a[i]);
				}
				else {
					hm.put(a[i], i);
					hs.add(i);
				}
			}
			int b[] = new int[n];
			int dist = 0;
			if(n==1) {
				b[0] = a[0];
			}
			else {
				if(ar.isEmpty()) {
					dist = n;
					int end = n;
					if(n%2!=0) {
						end = n-3;
						b[n-1] = a[n-2];
						b[n-2] = a[n-3];
						b[n-3] = a[n-1];
					}
					for(int i=0;i<end;i+=2) {
						b[i] = a[i+1];
						b[i+1] = a[i];
					}
				}
				else {
					if(n==2) {
						b[0] = a[0];
						b[1] = a[1];
						dist += 0;
					}
					else {
						int end = ar.size();
						if(end%2!=0)
							end--;
						for(int i=0;i<end;i+=2) {
							Pair x = ar.get(i);
							Pair y = ar.get(i+1);
							b[x.x] = a[y.x];
							b[x.y] = a[y.y];
							b[y.x] = a[x.x];
							b[y.y] = a[x.y];
							dist += 4;
						}
						if(ar.size()%2!=0) {
							Iterator<Integer> it = hs.iterator();
							if(hs.size()>1) {
								int x = it.next();
								int y = it.next();
								Pair z = ar.get(end);
								b[z.x] = a[x];
								b[z.y] = a[y];
								b[x] = a[z.x];
								b[y] = a[z.y];
								hs.remove(x);
								hs.remove(y);
								dist += 4;
							}
							else if(hs.size()==1) {
								int x = it.next();
								Pair z = ar.get(end);
								b[z.x] = a[x];
								b[x] = a[z.x];
								b[z.y] = a[z.y];
								hs.remove(x);
								dist += 2;
							}
							else {
								Pair z = ar.get(end);
								b[z.x] = a[z.x];
								b[z.y] = a[z.y];
								dist += 0;
							}
						}
						if(hs.size()!=0) {
							if(hs.size()==1) {
								int x = hs.iterator().next(); 
								b[x] = a[x];
								dist += 0;
							}
							else {
								dist += hs.size();
								end = hs.size();
								Iterator<Integer> it = hs.iterator();
								if(end%2!=0) {
									end = n-3;
									int x = it.next();
									int y = it.next();
									int z = it.next();
									b[x] = a[y];
									b[y] = a[z];
									b[z] = a[x];
								}
								for(int i=0;i<end;i+=2) {
									int x = it.next();
									int y = it.next();
									b[x] = a[y];
									b[y] = a[x];
								}
							}
						}
					}
				}
			}
			pw.println(dist);
			for(int i=0;i<n;i++)
				pw.print(b[i]+" ");
			pw.println();
		}
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