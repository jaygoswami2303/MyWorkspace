import java.io.*;
import java.util.*;
 
public class E 
{
	
	public static void main(String[] args) 
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		long n = ir.nextLong();
		int count = 0;
		long temp = n;
		while(temp!=0)
		{
			count++;
			temp/=10;
		}
		int two = -1;
		int five = -1;
		int zero = -1;
		int zero1 = -1;
		int seven = -1;
		int digits[] = new int[count];
		for(int i=count-1;i>=0;i--)
		{
			digits[i] = (int) (n%10);
			if(digits[i]==2 && two==-1)
				two = i;
			else if(digits[i]==5 && five==-1)
				five = i;
			else if(digits[i]==0 && zero==-1)
				zero = i;
			else if(digits[i]==0 && zero1==-1)
				zero1 = i;
			else if(digits[i]==7 && seven==-1)
				seven = i;
			n /= 10;
		}
		int min = -1;
		
		if(two!=-1 && five!=-1)
		{
			if(count>=2)
			{
				if(count>2)
				{
					if(two==0 && five==1 && digits[2]==0)
					{
						
					}
					else if(two==1 && five==0 && digits[2]==0)
					{
						
					}
					else if(two==0 && digits[1]==0)
					{
						
					}
					else if(five==0 && digits[1]==0)
					{
						
					}
					else if(two==count-1 && five==count-2)
					{
						if(min==-1 || min>1)
							min = 1;
					}
					else
					{
						int sum = count-2-two+count-1-five;
						if(two>five)
							sum++;
						if(min==-1 || sum<min)
							min = sum;
					}
				}
				else
				{
					if(two==count-1 && five==count-2)
					{
						if(min==-1 || min>1)
							min = 1;
					}
					else
						if(min==-1 || min>0)
							min = 0;
				}
			}
		}
		
		if(five!=-1 && zero!=-1)
		{
			if(count>=2)
			{
				if(count>2)
				{
					if(five==0 && zero==1 && digits[2]==0)
					{
						
					}
					else if(five==1 && zero==0 && digits[2]==0)
					{
						
					}
					else if(five==0 && digits[1]==0 && zero!=1)
					{
						
					}
					else if(zero==0 && digits[1]==0)
					{
						
					}
					else if(five==count-1 && zero==count-2)
					{
						if(min==-1 || min>1)
							min = 1;
					}
					else
					{
						int sum = count-2-five+count-1-zero;
						if(five>zero)
							sum++;
						if(min==-1 || sum<min)
							min = sum;
					}
				}
				else
				{
					if(five==count-1 && zero==count-2)
					{
						if(min==-1 || min>1)
							min = 1;
					}
					else
						if(min==-1 || min>0)
							min = 0;
				}
			}
		}
		
		if(seven!=-1 && five!=-1)
		{
			if(count>=2)
			{
				if(count>2)
				{
					if(seven==0 && five==1 && digits[2]==0)
					{
						
					}
					else if(seven==1 && five==0 && digits[2]==0)
					{
						
					}
					else if(seven==0 && digits[1]==0)
					{
						
					}
					else if(five==0 && digits[1]==0)
					{
						
					}
					else if(seven==count-1 && five==count-2)
					{
						if(min==-1 || min>1)
							min = 1;
					}
					else
					{
						int sum = count-2-seven+count-1-five;
						if(seven>five)
							sum++;
						if(min==-1 || sum<min)
							min = sum;
					}
				}
				else
				{
					if(seven==count-1 && five==count-2)
					{
						if(min==-1 || min>1)
							min = 1;
					}
					else
						if(min==-1 || min>0)
							min = 0;
				}
			}
		}
		
		if(zero1!=-1 && zero!=-1)
		{
			if(count>=2)
			{
				if(count>2)
				{
					if(zero1==0 && zero==1 && digits[2]==0)
					{
						
					}
					else if(zero1==1 && zero==0 && digits[2]==0)
					{
						
					}
					else if(zero1==0 && digits[1]==0)
					{
						
					}
					else if(zero==0 && digits[1]==0)
					{
						
					}
					else if(zero1==count-1 && zero==count-2)
					{
						if(min==-1 || min>1)
							min = 1;
					}
					else
					{
						int sum = count-2-zero1+count-1-zero;
						if(zero1>zero)
							sum++;
						if(min==-1 || sum<min)
							min = sum;
					}
				}
				else
				{
					if(zero1==count-1 && zero==count-2)
					{
						if(min==-1 || min>1)
							min = 0;
					}
					else
						if(min==-1 || min>0)
							min = 0;
				}
			}
		}
		
		pw.print(min);
		
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
				} catch (IOException e) 
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