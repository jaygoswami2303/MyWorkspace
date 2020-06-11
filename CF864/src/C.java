import java.io.*;
import java.util.*; 

public class C {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int current = b;
		boolean possible = true;
		int ans = 0;
		for(int i=0;i<k && possible;i++) {
			if(i%2==0) {
				if(i==k-1) {
					if((i==0 && current<f) || (i!=0 && current<2*f))
						possible = false;
					else {
						if(i==0)
							current = current-f;
						else
							current = current-2*f;
						if(current<a-f) {
							current = b;
							if(current<a-f)
								possible = false;
							ans++;
						}
					}
				}
				else if((i==0 && current<f) || (i!=0 && current<2*f))
					possible = false;
				else {
					if(i==0)
						current = current-f;
					else
						current = current-2*f;
					if(current<2*(a-f)) {
						current = b;
						ans++;
					}
				}
			}
			else {
				if(i==k-1) {
					if(current<2*(a-f))
						possible = false;
					else {
						current -= 2*(a-f);
						if(current<f) {
							current = b;
							if(current<f)
								possible = false;
							ans++;
						}
					}
				}
				else if(current<2*(a-f))
					possible = false;
				else {
					current -= 2*(a-f);
					if(current<2*f) {
						current = b;
						ans++;
					}
				}
			}
		}
		if(possible)
			pw.print(ans);
		else
			pw.print(-1);
		pw.close();
	}
}