import java.io.*;
import java.util.*;
 
public class B {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = sc.nextInt();
		String s = sc.next();
		sc.close();
		HashSet<Character> hs = new HashSet<Character>();
		int max = 0;
		for(int i=0;i<n;i++) {
			if(s.charAt(i)>='A' && s.charAt(i)<='Z') {
				if(hs.size()>max)
					max = hs.size();
				hs = new HashSet<Character>();
			}
			else {
				hs.add(s.charAt(i));
			}
		}
		if(hs.size()>max)
			max = hs.size();
		pw.print(max);
		pw.close();
	}
}