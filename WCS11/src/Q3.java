import java.io.*;
import java.util.*;
 
public class Q3 {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int q = ir.nextInt();
		HashMap<String, TreeSet<Integer>> currenthm = new HashMap<String, TreeSet<Integer>>();
		HashMap<String, TreeSet<Integer>> deletedhm = new HashMap<String, TreeSet<Integer>>();
		while(q>0) {
			String command = ir.readString();
			String name = ir.readString();
			if(command.charAt(0)=='c') {
				String ss[] = new String[2];
				int min = 0;
				if(name.contains("(")) {
					int f = name.indexOf('(');
					int l = name.indexOf(')');
					ss[0] = name.substring(0, f);
					ss[1] = name.substring(f+1,l);
					min = Integer.parseInt(ss[1]);
					name = ss[0];
				}
				if(currenthm.containsKey(name)) {
					TreeSet<Integer> cts = currenthm.get(name);
					TreeSet<Integer> dts = new TreeSet<>();
					if(deletedhm.containsKey(name))
						 dts = deletedhm.get(name);
					if(min==0 || currenthm.get(name).contains(min)) {
						if(dts.isEmpty())
							min = cts.last()+1;
						else
							min = dts.first();
					}
					cts.add(min);
					dts.remove(min);
					for(int i=0;i<min;i++)
						if(!cts.contains(i))
							dts.add(i);
					currenthm.put(name, cts);
					if(!dts.isEmpty())
						deletedhm.put(name, dts);
					else
						deletedhm.remove(name);
					pw.print("+ " + name);
					if(min!=0)
						pw.print("(" + min + ")");
					pw.println();
				}
				else {
					TreeSet<Integer> ts = new TreeSet<>();
					ts.add(min);
					TreeSet<Integer> dts = new TreeSet<>();
					if(deletedhm.containsKey(name))
						 dts = deletedhm.get(name);
					dts.remove(min);
					for(int i=0;i<min;i++)
						dts.add(i);
					if(!dts.isEmpty())
						deletedhm.put(name, dts);
					else
						deletedhm.remove(name);
					currenthm.put(name, ts);
					pw.print("+ " + name);
					if(min!=0)
						pw.print("(" + min + ")");
					pw.println();
				}
			}
			else if(command.charAt(0)=='d') {
				String ss[] = new String[2];
				int remove = 0;
				if(name.contains("(")) {
					int f = name.indexOf('(');
					int l = name.indexOf(')');
					ss[0] = name.substring(0, f);
					ss[1] = name.substring(f+1,l);
					remove = Integer.parseInt(ss[1]);
					name = ss[0];
				}
				TreeSet<Integer> cts = currenthm.get(name);
				TreeSet<Integer> dts = new TreeSet<>();
				if(deletedhm.containsKey(name))
					dts = deletedhm.get(name);
				cts.remove(remove);
				dts.add(remove);
				if(!cts.isEmpty())
					currenthm.put(name, cts);
				else
					currenthm.remove(name);
				deletedhm.put(name, dts);
				pw.print("- " + name);
				if(remove!=0)
					pw.print("(" + remove + ")");
				pw.println();
			}
			else {
				pw.print("r " + name + " -> ");
				String ss[] = new String[2];
				int remove = 0;
				if(name.contains("(")) {
					int f = name.indexOf('(');
					int l = name.indexOf(')');
					ss[0] = name.substring(0, f);
					ss[1] = name.substring(f+1,l);
					remove = Integer.parseInt(ss[1]);
					name = ss[0];
				}
				TreeSet<Integer> cts = currenthm.get(name);
				TreeSet<Integer> dts = new TreeSet<>();
				if(deletedhm.containsKey(name))
					dts = deletedhm.get(name);
				cts.remove(remove);
				dts.add(remove);
				if(!cts.isEmpty())
					currenthm.put(name, cts);
				else
					currenthm.remove(name);
				deletedhm.put(name, dts);
				
				name = ir.readString();
				int min = 0;
				if(name.contains("(")) {
					int f = name.indexOf('(');
					int l = name.indexOf(')');
					ss[0] = name.substring(0, f);
					ss[1] = name.substring(f+1,l);
					min = Integer.parseInt(ss[1]);
					name = ss[0];
				}
				if(currenthm.containsKey(name)) {
					cts = currenthm.get(name);
					dts = new TreeSet<>();
					if(deletedhm.containsKey(name))
						 dts = deletedhm.get(name);
					if(min==0 || currenthm.get(name).contains(min)) {
						if(dts.isEmpty())
							min = cts.last()+1;
						else
							min = dts.first();
					}
					cts.add(min);
					dts.remove(min);
					for(int i=0;i<min;i++)
						if(!cts.contains(min))
							dts.add(i);
					currenthm.put(name, cts);
					if(!dts.isEmpty())
						deletedhm.put(name, dts);
					else
						deletedhm.remove(name);
					pw.print(name);
					if(min!=0)
						pw.print("(" + min + ")");
					pw.println();
				}
				else {
					TreeSet<Integer> ts = new TreeSet<>();
					ts.add(min);
					dts = new TreeSet<>();
					if(deletedhm.containsKey(name))
						 dts = deletedhm.get(name);
					dts.remove(min);
					for(int i=0;i<min;i++)
						dts.add(i);
					if(!dts.isEmpty())
						deletedhm.put(name, dts);
					else
						deletedhm.remove(name);

					currenthm.put(name, ts);
					pw.print(name);
					if(min!=0)
						pw.print("(" + min + ")");
					pw.println();
				}
				
			}
			q--;
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