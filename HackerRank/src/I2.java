import java.util.Scanner;

public class I2 {

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int h[] = new int[26];
		for(int i=0;i<26;i++)
			h[i] = sc.nextInt();
		sc.nextLine();
		String s = sc.nextLine();
		sc.close();
		int wi = s.length();
		int he = h[s.charAt(0)-'a'];
		for(int i=1;i<wi;i++)
			if(he<h[s.charAt(i)-'a'])
				he = h[s.charAt(i)-'a'];
		int area = wi*he;
		System.out.print(area);
	}
}
