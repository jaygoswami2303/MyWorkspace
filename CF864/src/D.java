import java.io.*;
import java.util.*; 

public class D {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int n = sc.nextInt();
		int a[] = new int[n];
		HashSet<Integer> hs[] = new HashSet[n];
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		boolean used[] = new boolean[n];
		for(int i=0;i<n;i++) {
			a[i] = sc.nextInt()-1;
			ts.add(i);
			hs[i] = new HashSet<Integer>();
		}
		for(int i=0;i<n;i++) {
			hs[a[i]].add(i);
			if(hs[a[i]].size()==2) {
				Iterator<Integer> it = hs[a[i]].iterator();
				while(it.hasNext())
					tm.put(it.next(), a[i]);
			}
			else if(hs[a[i]].size()>2) {
				tm.put(i, a[i]);
			}
			ts.remove(a[i]);
		}
		int ans = ts.size();
		while(tm.size()>0) {
			int element = ts.first();
			int key = tm.firstKey();
			if(element<a[key] || used[a[key]]) {
				hs[a[key]].remove(key);
				if(hs[a[key]].size()==1 && !used[a[key]])
					tm.remove(hs[a[key]].iterator().next());
				a[key] = element;
				tm.remove(key);
				ts.remove(element);
			}
			else {
				tm.remove(key);
				used[a[key]] = true;
			}
		}
		pw.println(ans);
		for(int i=0;i<n;i++)
			pw.print((a[i]+1) + " ");
		sc.close();
		pw.close();
	}
}