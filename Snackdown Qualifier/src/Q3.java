import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class Q3 {

	public static void main(String args[]){
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int t=in.nextInt();
		while(t-->0)
		{
			int n=in.nextInt();
			int q=in.nextInt();
			long arr[]=new long[n];
			for(int i=0;i<n;i++)
			{
				long x=in.nextLong();
				arr[i]=x;
			}
			Arrays.sort(arr);
			long ar[]=new long[n+1];
			ar[n]=0;
			ar[n-1]=arr[n-1];
			for(int i=1;i<n;i++)
			{
				int inx=n-i-1;
				ar[inx]=ar[inx+1]+arr[inx];
			}
			while(q-->0)
			{
				long x=in.nextLong();
				int inx=Arrays.binarySearch(arr, x);
				if(inx<0)
					inx=-1*(inx+1);
				int b=0;
				int e=inx-1;
				int mid=-1;
				int ans = n-inx;
				while(b<=e)
				{
					mid=(b+e)/2;
					long p=ar[mid];
					long f=ar[inx];
					long diff=(((long)(inx-mid))*x)-(p-f); 
					if(diff==mid){
						ans = n - mid;
						break;
					}
					else if(diff>mid)
					{
						b=mid+1;
					}
					else{
						ans = n - mid;
						e=mid-1;
					}
					
				}
				out.println(ans);
			}
		}
		out.close();
		
}

static class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream inputstream) {
		reader = new BufferedReader(new InputStreamReader(inputstream));
		tokenizer = null;
	}

	public String nextLine(){
		String fullLine=null;
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				fullLine=reader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return fullLine;
		}
		return fullLine;
	}
	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}
	public long nextLong() {
		return Long.parseLong(next());
	}
	public int nextInt() {
		return Integer.parseInt(next());
	}
}
}