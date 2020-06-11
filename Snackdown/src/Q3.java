import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Q3 {

	public static void main(String args[]){
		InputReader in = new InputReader(System.in);
		//OutputStream outputStream = System.out;
		PrintWriter out = new PrintWriter(System.out);
		int t=in.nextInt();
		while(t-->0)
		{
			int n=in.nextInt();
			int q=in.nextInt();
			int arr[]=new int[n];
			for(int i=0;i<n;i++)
			{
				int x=in.nextInt();
				arr[i]=x;
			}
			Arrays.sort(arr);
			int ar[]=new int[n+1];
			ar[n]=0;
			ar[n-1]=arr[n-1];
			for(int i=1;i<n;i++)
			{
				int inx=n-i-1;
				ar[inx]=ar[inx+1]+arr[inx];
			}
			/*for(int i=0;i<n;i++)
			{
				out.print(ar[i]+" ");
			}*/
			//out.println();
			while(q-->0)
			{
				int x=in.nextInt();
				int inx=Arrays.binarySearch(arr, x);
				//out.println(inx);
				//Arrays.b
				if(inx<0)
					inx=-1*(inx+1);
				int c=n-inx;
				//out.println("c1="+c+"inx="+inx);
				int b=0;
				int e=inx-1;
				int mid=-1;
				//int g=0;
				int ans = 0;
				while(b<=e)
				{
					mid=(b+e)/2;
					int p=ar[mid];
					int f=ar[inx];
					int diff=((inx-mid)*x)-(p-f);
					if(diff==mid){
						//c=c+mid;
						break;
					}
					else if(diff>mid)
					{
						b=mid+1;
					}
					else{
						e=mid-1;
						ans = inx-mid;
					}
					
				}
				if(mid!=-1&&((((inx-mid)*x)-(ar[mid]-ar[inx]))<=mid)){
					c=c+(inx-mid);
				}
				else {
					c += ans;
				}
				//out.println("\n"+mid+" "+c);
				out.println(c);
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