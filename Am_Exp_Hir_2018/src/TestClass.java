import java.io.*;
import java.util.*;
 
public class TestClass {
	
	static long sum;
	static long mod;
	static long fact[];
	static long invfact[];
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		int k = ir.nextInt();
		int a[] = new int[n];
		for(int i=0;i<n;i++)
			a[i] = ir.nextInt();
		mod = 998244353;
		sum = 0;
		int N = 50001;
        fact = new long[N];
        invfact = new long[N];
        fact[0] = 1;
        for(int i=1;i<N;i++)
            fact[i] = (fact[i - 1] * i) % mod;
        invfact[N-1] = modulo(fact[N-1], mod - 2, mod);
        for(int i=N-2;i>=0;i--) {
            invfact[i] = (invfact[i + 1] * (i + 1)) % mod;
        }
        int count[] = new int[N];
        long sum[] = new long[N];
        
        for(int i = 0; i < n; i++) {
            count[a[i]]++;
            sum[a[i]] += a[i];
        }
        
        for(int i=1;i<N;i++) {
            for(int j=i+i;j<N;j+=i) {
                count[i] += count[j];
                sum[i] += sum[j];
            }
        }
        long ans = 0;
        
        for(int i=N-1;i>=0;i--) {
            if(count[i] != 0) {
	            long ncr = nCr(count[i] - 1, k - 1);
	            sum[i] = (sum[i] * ncr) % mod;
	            for(int j =i+i;j<N;j+=i)
	                sum[i] = ((sum[i]-sum[j])%mod+mod)%mod;
	            long cur = (i*sum[i])%mod;
	            ans = (ans+cur)%mod;
            }
        }
		pw.print(ans);
		pw.close();
	}
	
	static long modulo(long a,long b,long c) {
		long x=1;
		long y=a;
		while(b > 0){
			if(b%2 == 1){
				x=(x*y)%c;
			}
			y = (y*y)%c;
			b /= 2;
		}
		return  x%c;
	}
	
	static long nCr(int n, int r) {
        if(n < r) 
        	return 0;
        return ((fact[n]*invfact[r])%mod*invfact[n-r])%mod;
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