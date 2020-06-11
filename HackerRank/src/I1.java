import java.util.Scanner;

public class I1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long sum=0,min=10000000000l,max=0;
        for(int i=0;i<5;i++)
        {
            long a = in.nextLong();
            sum = sum+a;
            if(a>max)
                max=a;
            if(a<min)
                min=a;
        }
        in.close();
        System.out.print((sum-max)+" "+(sum-min));
    }
}
