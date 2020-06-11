import java.util.*;
public class heyhey {
    public static void main(String[] args){
        HashMap<Pair, Integer> hs = new HashMap<Pair, Integer>();
        for(int i=0;i<=10;i++)
        	hs.put(new Pair(0,i), i);
        Iterator<Pair> it = hs.keySet().iterator();
        for(Map.Entry<Pair, Integer> temp:hs.entrySet())
        	System.out.println(temp);
    }
}

class Pair {
	int x;
	int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.x);
	}
	
	@Override
	public boolean equals(Object o) {
		Pair p = (Pair)o;
		return this.x == p.x;
	}
	
	@Override
	public String toString() {
		return new String(this.x + " " + this.y);
	}
}