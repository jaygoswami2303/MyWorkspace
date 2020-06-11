import java.io.*;                                                       
import java.util.*;
 
public class Q2
{
    public static ArrayList<Integer> adj[];
    public static boolean v[];
    public static int h[];
    public static void main(String args[])
    {
        InputReader sc = new InputReader(System.in);
        PrintWriter pr = new PrintWriter(System.out);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int mat[][] = new int[n][m];
        adj = new ArrayList[n*m];
        v = new boolean[n*m];
        h = new int[n*m];
        for (int i = 0; i < n*m; i++) {
            h[i] = -1;  
        }
        for (int i = 0; i < n*m; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        edge(mat, n, m);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int start = x*m+y;
        int end = a*m+b;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(start);
        v[start] = true;
        h[start] = 0;
        int temp, current;
        ArrayList<Integer> ar;
        if(adj[end].size()!=0) {
	        while(q.size()!=0 && h[end]==-1){
	            temp = q.poll();
	            ar = adj[temp];
	            for (int i = 0; i < ar.size(); i++) {
	            	current = ar.get(i); 
	                if (!v[current]) {
	                    q.add(current);
	                    v[current] = true;
	                    h[current] = h[temp]+1;
	                    if(h[end]!=-1)
	                    	break;
	                }
	            }
	        }
        }
        pr.println(h[end]);
        pr.close();
    }
    
    public static void edge(int mat[][],int n,int m){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j>0&&mat[i][j-1]!=0) {
                    adj[i*m+j].add(i*m+(j-1));
                }
                if (i>0&&mat[i-1][j]!=0) {
                    adj[i*m+j].add((i-1)*m+j);
                }
                if (j<m-1&&mat[i][j+1]!=0) {
                    adj[i*m+j].add(i*m+(j+1));
                }
                if (i<n-1&&mat[i+1][j]!=0) {
                    adj[i*m+j].add((i+1)*m+j);
                }
            }
        }
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

            public int nextInt() 
            {
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

            public long nextLong() 
            {
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
                    do {
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