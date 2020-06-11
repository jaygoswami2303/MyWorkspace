package abcd;

public class hey {

	public static void main(String[] args) {
		long a = 560;
		long b = 344;
		long temp = gcd(a,b);
		while(temp!=1) {
			System.out.println(temp);
			temp = gcd(a/temp,b/temp);
		}
	}
	
	public static long gcd(long a,long b) {
		if(a==0)
			return b;
		return gcd(b%a,a);
	}

}
