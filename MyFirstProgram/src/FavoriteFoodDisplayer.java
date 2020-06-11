
public class FavoriteFoodDisplayer {

	public static void main(String[] args) {
		long ar[] = new long[100];
		ar[0] = 0l;
		for(int i=1;i<ar.length;i++)
			ar[i] = ar[i-1]^i;
		for(int i=0;i<ar.length;i++)
			System.out.print(ar[i]+" ");
	}

}
