import java.io.*;
import java.util.*;
 
public class C 
{
	
	public static void main(String[] args) 
	{
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		double r = ir.nextInt();
		double x1 = ir.nextInt();
		double y1 = ir.nextInt();
		double x2 = ir.nextInt();
		double y2 = ir.nextInt();
		if(x1==x2 && y1==y2) {
			pw.print(x1+r/2 + " " + y1 + " " + r/2);
		}
		else if((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) < r*r) {
			List<Point> list = getCircleLineIntersectionPoint(new Point(x1,y1),
		            new Point(x2,y2), new Point(x1,y1), r);
			Point temp = list.get(0);
			double dist = (x2-temp.x)*(x2-temp.x) + (y2-temp.y)*(y2-temp.y);
			Point temp1 = list.get(1);
			double dist1 = (x2-temp1.x)*(x2-temp1.x) + (y2-temp1.y)*(y2-temp1.y);
			if(dist1>dist)
				temp = temp1;
			temp1.x = (temp.x+x2)/2;
			temp1.y = (temp.y+y2)/2;
			double rad = Math.sqrt((temp1.x - x2)*(temp1.x - x2) + (temp1.y - y2)*(temp1.y - y2));
			double xt = temp1.x;
			double yt = temp1.y + rad;
			double xt1 = temp1.x + rad;
			double yt1 = temp1.y;
			double change = 1;
			while( (((xt-x1)*(xt-x1) + (yt-y1)*(yt-y1) > r*r) || ((xt1-x1)*(xt1-x1) + (yt1-y1)*(yt1-y1) > r*r)) && change>0.00000001) {
				while( ( ( (xt-x1)*(xt-x1) + (yt-y1)*(yt-y1) ) > r*r) || ( ( (xt1-x1)*(xt1-x1) + (yt1-y1)*(yt1-y1) > r*r) )) {
					rad -= change;
					xt = temp1.x;
					yt = temp1.y + rad;
					xt1 = temp1.x + rad;
					yt1 = temp1.y;
				}
				rad += change;
				xt = temp1.x;
				yt = temp1.y + rad;
				xt1 = temp1.x + rad;
				yt1 = temp1.y;
				change /= 10;
			}
			pw.print(temp1.x + " " + temp1.y + " " + rad);
		}
		else {
			pw.print(x1 + " " + y1 + " " + r);
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
			} 
			while (!isSpaceChar(c));
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
	
	public static List<Point> getCircleLineIntersectionPoint(Point pointA,
            Point pointB, Point center, double radius) {
        double baX = pointB.x - pointA.x;
        double baY = pointB.y - pointA.y;
        double caX = center.x - pointA.x;
        double caY = center.y - pointA.y;

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - radius * radius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        if (disc < 0) {
            return Collections.emptyList();
        }
        // if disc == 0 ... dealt with later
        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        Point p1 = new Point(pointA.x - baX * abScalingFactor1, pointA.y
                - baY * abScalingFactor1);
        if (disc == 0) { // abScalingFactor1 == abScalingFactor2
            return Collections.singletonList(p1);
        }
        Point p2 = new Point(pointA.x - baX * abScalingFactor2, pointA.y
                - baY * abScalingFactor2);
        return Arrays.asList(p1, p2);
    }

    static class Point {
        double x, y;

        public Point(double x, double y) { this.x = x; this.y = y; }

        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
    }
 
}