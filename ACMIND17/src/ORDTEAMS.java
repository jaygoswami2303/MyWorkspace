import java.io.*;
import java.util.*;
 
public class ORDTEAMS
{
	
	public static void main(String[] args)
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t = ir.nextInt();
		while(t-->0) {
			int s[][] = new int[3][3];
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					s[i][j] = ir.nextInt();
				}
			}
			boolean possible = false;
			int k = -1;
			for(int i=0;i<3;i++) {
				boolean complete = true;
				for(int j=0;j<3 && complete;j++) {
					if(j!=i) {
						if(s[i][0]>=s[j][0] && s[i][1]>=s[j][1] && s[i][2]>=s[j][2]) {
							if(s[i][0]>s[j][0] || s[i][1]>s[j][1] || s[i][2]>s[j][2]) {
								complete = true;
							}
							else {
								complete = false;
							}
						}
						else {
							complete = false;
						}
					}
				}
				if(complete) {
					k=i;
					break;
				}
			}
			if(k!=-1) {
				int l = -1;
				for(int i=0;i<3;i++) {
					if(i!=k) {
						boolean complete = true;
						for(int j=0;j<3 && complete;j++) {
							if(j!=i && j!=k) {
								if(s[i][0]>=s[j][0] && s[i][1]>=s[j][1] && s[i][2]>=s[j][2]) {
									if(s[i][0]>s[j][0] || s[i][1]>s[j][1] || s[i][2]>s[j][2]) {
										complete = true;
									}
									else {
										complete = false;
									}
								}
								else {
									complete = false;
								}
							}
						}
						if(complete) {
							l=i;
							break;
						}
					}
				}
				if(l!=-1) {
					possible = true;
				}
			}
			if(possible)
				pw.println("yes");
			else
				pw.println("no");
		}
		pw.close();
	}
		
	static class InputReader
	{
 
		private final InputStream stream;
		private final byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;
 
		public InputReader(InputStream stream)
		{
			this.stream = stream;
		}
 
		public int snext()
		{
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars)
			{
				curChar = 0;
				try
				{
					snumChars = stream.read(buf);
				}
				catch (IOException e)
				{
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}
 
		public int nextInt()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public long nextLong()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public int[] nextIntArray(int n)
		{
			int a[] = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
 
		public String readString()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do
			{
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}
 
		public boolean isSpaceChar(int c)
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
		public interface SpaceCharFilter
		{
			public boolean isSpaceChar(int ch);
		}
	}
 
}