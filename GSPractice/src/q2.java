import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class q2 {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(longestSubstring(s));
    }
    
    static int longestSubstring(String input) {
    	int s=0;
    	int e=1;
    	int max = 1;
    	HashSet<Character> hs = new HashSet<Character>();
    	hs.add(input.charAt(s));
    	while(s<input.length()) {
    		if(e<input.length() && !hs.contains(input.charAt(e))) {
    			hs.add(input.charAt(e));
    			e++;
    			if(e-s+1>max)
    				max=e-s;
    		}
    		else {
    			hs.remove(input.charAt(s));
    			s++;
    		}
    	}
    	return max;
    }
}