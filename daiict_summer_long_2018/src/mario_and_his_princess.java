import java.io.*;
import java.util.*;
 
public class mario_and_his_princess {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = ir.nextInt();
		int m = ir.nextInt();
		int h = ir.nextInt();
		int k = ir.nextInt();
		int grid[][] = new int[n][m];
		for(int i=0;i<n;i++) {
			String s = ir.readString();
			for(int j=0;j<m;j++) {
				char a = s.charAt(j);
				if(a=='D')
					grid[i][j]=-1;
				else if(a=='L')
					grid[i][j] = k;
			}
		}
		int x1 = ir.nextInt()-1;
		int y1 = ir.nextInt()-1;
		int x2 = ir.nextInt()-1;
		int y2 = ir.nextInt()-1;
		int ans = shortestPath(grid,x2,y2,x1,y1,h);
		if(ans>0)
			pw.print(ans);
		else
			pw.print("Sorry Mario");
		pw.close();
	}
	
	static int shortestPath(int grid[][], int x1, int y1, int x2, int y2, int h)
	{
		int n = grid.length;
		int m = grid[0].length;
	    int dis[][] = new int[n][m];
	 
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < m; j++)
	            dis[i][j] = -1;
	 
	    int dx[] = {-1, 0, 1, 0};
	    int dy[] = {0, 1, 0, -1};
	    
	    PriorityQueue<Cell> pq = new PriorityQueue<Cell>(new Comparator<Cell>() {

			@Override
			public int compare(Cell x, Cell y) {
				return Integer.compare(x.value, y.value);
			}
	    	
	    });
	 
	    pq.add(new Cell(x1, y1, 0));
	 
	    dis[x1][y1] = 0;
	 
	    while(!pq.isEmpty())
	    {
	    	Cell k = pq.poll();
	    	int p = k.x;
	    	int q = k.y;
	    	int d = dis[p][q];
	    	if(d==k.value) {
	 
		    	for (int i = 0; i < 4; i++)
		        {
		            int x = p + dx[i];
		            int y = q + dy[i];
		 
		            if (x>=0 && x<n && y>=0 && y<m && grid[x][y]!=-1) {
		            	int di = dis[x][y];
			            if (di==-1 || di > d + grid[x][y])
			            {
			                dis[x][y] = d + grid[x][y];
			                pq.add(new Cell(x, y, dis[x][y]));
			            }
		            }
		        }
	    	}
	    }
	    if(grid[x1][y1]==-1 || dis[x2][y2]==-1 || dis[n-1][m-1]==-1)
	    	return -1;
	    return h - dis[x2][y2] - dis[n-1][m-1] - grid[x1][y1];
	}
	
	static class Cell {
		int x;
		int y;
		int value;
		
		public Cell(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
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