import java.io.*;
import java.util.*;

class HungarianBipartiteMatching
{

    private final double[][] costMatrix;
    private final int        rows, cols, dim;
    private final double[]   labelByWorker, labelByJob;
    private final int[]      minSlackWorkerByJob;
    private final double[]   minSlackValueByJob;
    private final int[]      matchJobByWorker, matchWorkerByJob;
    private final int[]      parentWorkerByCommittedJob;
    private final boolean[]  committedWorkers;
    
    public HungarianBipartiteMatching(double[][] costMatrix)
    {
        this.dim = Math.max(costMatrix.length, costMatrix[0].length);
        this.rows = costMatrix.length;
        this.cols = costMatrix[0].length;
        this.costMatrix = new double[this.dim][this.dim];
        
        for (int w = 0; w < this.dim; w++)
        {
            if (w < costMatrix.length)
            {
                if (costMatrix[w].length != this.cols)
                {
                    throw new IllegalArgumentException("Irregular cost matrix");
                }
                this.costMatrix[w] = Arrays.copyOf(costMatrix[w], this.dim);
            }
            else
            {
                this.costMatrix[w] = new double[this.dim];
            }
        }

        labelByWorker = new double[this.dim];
        labelByJob = new double[this.dim];
        minSlackWorkerByJob = new int[this.dim];
        minSlackValueByJob = new double[this.dim];
        committedWorkers = new boolean[this.dim];
        parentWorkerByCommittedJob = new int[this.dim];
        matchJobByWorker = new int[this.dim];
        Arrays.fill(matchJobByWorker, -1);
        matchWorkerByJob = new int[this.dim];
        Arrays.fill(matchWorkerByJob, -1);
    }

    protected void computeInitialFeasibleSolution()
    {
        for (int j = 0; j < dim; j++)
        {
            labelByJob[j] = Double.POSITIVE_INFINITY;
        }
        
        for (int w = 0; w < dim; w++)
        {
            for (int j = 0; j < dim; j++)
            {
                if (costMatrix[w][j] < labelByJob[j])
                {
                    labelByJob[j] = costMatrix[w][j];
                }
            }
        }
    }
    
    public int[] execute()

    {

        /*

         * Heuristics to improve performance: Reduce rows and columns by their

         * smallest element, compute an initial non-zero dual feasible solution

         * and

         * create a greedy matching from workers to jobs of the cost matrix.

         */

        reduce();

        computeInitialFeasibleSolution();

        greedyMatch();

        int w = fetchUnmatchedWorker();

        while (w < dim)

        {

            initializePhase(w);

            executePhase();

            w = fetchUnmatchedWorker();

        }

        int[] result = Arrays.copyOf(matchJobByWorker, rows);

        for (w = 0; w < result.length; w++)

        {

            if (result[w] >= cols)

            {

                result[w] = -1;

            }

        }

        return result;

    }

 

    protected void executePhase()

    {

        while (true)

        {

            int minSlackWorker = -1, minSlackJob = -1;

            double minSlackValue = Double.POSITIVE_INFINITY;

            for (int j = 0; j < dim; j++)

            {

                if (parentWorkerByCommittedJob[j] == -1)

                {

                    if (minSlackValueByJob[j] < minSlackValue)

                    {

                        minSlackValue = minSlackValueByJob[j];

                        minSlackWorker = minSlackWorkerByJob[j];

                        minSlackJob = j;

                    }

                }

            }

            if (minSlackValue > 0)

            {

                updateLabeling(minSlackValue);

            }

            parentWorkerByCommittedJob[minSlackJob] = minSlackWorker;

            if (matchWorkerByJob[minSlackJob] == -1)

            {

                /*

                 * An augmenting path has been found.

                 */

                int committedJob = minSlackJob;

                int parentWorker = parentWorkerByCommittedJob[committedJob];

                while (true)

                {

                    int temp = matchJobByWorker[parentWorker];

                    match(parentWorker, committedJob);

                    committedJob = temp;

                    if (committedJob == -1)

                    {

                        break;

                    }

                    parentWorker = parentWorkerByCommittedJob[committedJob];

                }

                return;

            }

            else

            {

                /*

                 * Update slack values since we increased the size of the

                 * committed

                 * workers set.

                 */

                int worker = matchWorkerByJob[minSlackJob];

                committedWorkers[worker] = true;

                for (int j = 0; j < dim; j++)

                {

                    if (parentWorkerByCommittedJob[j] == -1)

                    {

                        double slack = costMatrix[worker][j]

                                - labelByWorker[worker] - labelByJob[j];

                        if (minSlackValueByJob[j] > slack)

                        {

                            minSlackValueByJob[j] = slack;

                            minSlackWorkerByJob[j] = worker;

                        }

                    }

                }

            }

        }

    }

 

    protected int fetchUnmatchedWorker()

    {

        int w;

        for (w = 0; w < dim; w++)

        {

            if (matchJobByWorker[w] == -1)

            {

                break;

            }

        }

        return w;

    }

 

    protected void greedyMatch()

    {

        for (int w = 0; w < dim; w++)

        {

            for (int j = 0; j < dim; j++)

            {

                if (matchJobByWorker[w] == -1

                        && matchWorkerByJob[j] == -1

                        && costMatrix[w][j] - labelByWorker[w] - labelByJob[j] == 0)

                {

                    match(w, j);

                }

            }

        }

    }

 

    protected void initializePhase(int w)

    {

        Arrays.fill(committedWorkers, false);

        Arrays.fill(parentWorkerByCommittedJob, -1);

        committedWorkers[w] = true;

        for (int j = 0; j < dim; j++)

        {

            minSlackValueByJob[j] = costMatrix[w][j] - labelByWorker[w]

                    - labelByJob[j];

            minSlackWorkerByJob[j] = w;

        }

    }

 

    protected void match(int w, int j)

    {

        matchJobByWorker[w] = j;

        matchWorkerByJob[j] = w;

    }

 

    protected void reduce()

    {

        for (int w = 0; w < dim; w++)

        {

            double min = Double.POSITIVE_INFINITY;

            for (int j = 0; j < dim; j++)

            {

                if (costMatrix[w][j] < min)

                {

                    min = costMatrix[w][j];

                }

            }

            for (int j = 0; j < dim; j++)

            {

                costMatrix[w][j] -= min;

            }

        }

        double[] min = new double[dim];

        for (int j = 0; j < dim; j++)

        {

            min[j] = Double.POSITIVE_INFINITY;

        }

        for (int w = 0; w < dim; w++)

        {

            for (int j = 0; j < dim; j++)

            {

                if (costMatrix[w][j] < min[j])

                {

                    min[j] = costMatrix[w][j];

                }

            }

        }

        for (int w = 0; w < dim; w++)

        {

            for (int j = 0; j < dim; j++)

            {

                costMatrix[w][j] -= min[j];

            }

        }

    }

 

    protected void updateLabeling(double slack)

    {

        for (int w = 0; w < dim; w++)

        {

            if (committedWorkers[w])

            {

                labelByWorker[w] += slack;

            }

        }

        for (int j = 0; j < dim; j++)

        {

            if (parentWorkerByCommittedJob[j] != -1)

            {

                labelByJob[j] -= slack;

            }

            else

            {

                minSlackValueByJob[j] -= slack;

            }

        }

    }
}


public class Q2 {
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		
		int T = ir.nextInt();
		while(T>0) {
			int N = ir.nextInt();
			int K = ir.nextInt();
			double[][] cost = new double[K][N];
			String sr[]=new String[N];
			String sra[]=new String[K];
			for(int i=0;i<N;i++)
			{
				sr[i]=ir.readString();
			}
			for(int i=0;i<K;i++)
			{
				sra[i]=ir.readString();
			}
			for(int i=0;i<N;i++)
			{
				String s1[]=sr[i].split(":");
				for(int j=0;j<K;j++)
				{
					String s2[]=sra[j].split(":");
					
					int ih=Integer.parseInt(s1[0]);
					int im=Integer.parseInt(s1[1]);
					int is=Integer.parseInt(s1[2]);
					
					int fh=Integer.parseInt(s2[0]);
					int fm=Integer.parseInt(s2[1]);
					int fs=Integer.parseInt(s2[2]);
					
					int units=0;
					int diff1=Math.max(fs, is)-Math.min(fs, is);
					int diff2=60-diff1;
					int diff=Math.min(diff1, diff2);
					if((is+diff-60)==fs){
						im=im+1;
						ih=(ih+(im/60))%24;
						im=im%60;
					}
					else if((is-diff+60)==fs){
						im=im-1;
						if(im<0){
							ih--;
							ih=(ih+24)%24;
							im=(im+60)%60;
						}
					}
					units=units+diff;
					
					diff1=Math.max(fm, im)-Math.min(fm, im);
					diff2=60-diff1;
					diff=Math.min(diff1, diff2);
					if((im+diff-60)==fm){
						ih=(ih+1)%24;
					}
					else if((im-diff+60)==fm){
						ih=(ih-1+24)%24;
					}
					units=units+diff;
					
					diff1=Math.max(fh, ih)-Math.min(fh, ih);
					diff2=60-diff1;
					diff=Math.min(diff1, diff2);
					units=units+diff;
					
					cost[j][i]=units;
				}
			}
			HungarianBipartiteMatching hbm = new HungarianBipartiteMatching(cost);
			int[] result = hbm.execute();
			int ans = 0;
			for(int i=0;i<result.length;i++)
				ans += cost[i][result[i]];
			pw.println(ans);
			T--;
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