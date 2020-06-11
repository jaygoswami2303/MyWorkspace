import java.util.ArrayList;
import java.util.Scanner;

public class Echo 
{
 public static void main(String[] args)
    {
	 Scanner scan = new Scanner(System.in);
	 int n = scan.nextInt();
     long a[] = new long[n];
     ArrayList<Long> ar = new ArrayList<Long>();
     for(int i=0;i<n;i++)
     {
    	 a[i] = scan.nextLong();
    	 if(!ar.contains(a[i]))
    		 ar.add(a[i]);
     }
     if(ar.size()>3)
    	 System.out.print("NO");
     else if(ar.size()==1 || ar.size()==2)
    	 System.out.print("YES");
     else
     {
    	 long x = ar.get(1) - ar.get(0);
    	 if(x<0)
    		 x = -x;
    	 long y = ar.get(2)-ar.get(1);
    	 if(y<0)
    		 y=-y;
    	 if(y==x || y==2*x || x==2*y)
    		 System.out.print("YES");
    	 else
    		 System.out.print("NO");
     }
	 scan.close();
    }
}