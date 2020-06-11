import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class q1 {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        ArrayList<HashMap<Integer,Integer>> factors = new ArrayList<HashMap<Integer,Integer>>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<n;i++) {
            a[i] = sc.nextInt();
            factors.add(getFactors(a[i]));
        }
        int max = 0;
        for(int i=0;i<n-1;i++) {
        	for(int j=i+1;j<n;j++) {
        		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
                Iterator<Integer> it1 = factors.get(i).keySet().iterator();
                Iterator<Integer> it2 = factors.get(j).keySet().iterator();
                while(it1.hasNext()) {
                    int temp = it1.next();
                    hm.put(temp,factors.get(i).get(temp));
                }
                while(it2.hasNext()) {
                    int temp = it2.next();
                    if(!hm.containsKey(temp))
                    	hm.put(temp, 0);
                    hm.put(temp,hm.get(temp)+factors.get(j).get(temp));
                }
                int sum = 1;
                it1 = hm.keySet().iterator();
                while(it1.hasNext()) {
                	sum *= (hm.get(it1.next())+1);
                }
                if(sum>max)
                	max = sum;
        	}
        }
        System.out.print(max);
        sc.close();
    }
    
    static HashMap<Integer,Integer> getFactors(int n) {
        HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
        int count = 0;
        while(n%2==0) {
            count++;
            n/=2;
        }
        hm.put(2,count);
        for(int i=3;i*i<=n;i=i+2) {
            if(n%i==0) {
                count=0;
                while(n%i==0) {
                    count++;
                    n/=i;
                }
                hm.put(i,count);
            }
        }
        if(n!=1)
            hm.put(n,1);
        return hm;
    }
}