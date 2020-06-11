package Lab1b;

import java.util.*;
import java.io.*;

public class SRTF {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int preEmpt = 1;
		int n = sc.nextInt();
		
		Process process[] = new Process[n];
		HashSet<Process> hs = new HashSet<Process>();
		TreeMap<Integer, Integer> tmAt = new TreeMap<Integer,Integer>();
		
		for(int i=0;i<n;i++) {
			process[i] = new Process(i,sc.nextInt(), sc.nextInt());
			hs.add(process[i]);
			tmAt.put(process[i].at,process[i].id);
		}
		sc.close();
		
		TreeMap<Integer, Integer> tm = new TreeMap<Integer,Integer>();
		
		for(int i=0;!hs.isEmpty() || !tm.isEmpty();) {
			while(!tmAt.isEmpty() && tmAt.firstKey()<=i) {
				int index = tmAt.firstEntry().getValue();
				tm.put(process[index].rt, process[index].id);
				hs.remove(process[index]);
				tmAt.remove(tmAt.firstKey());
			}
			if(!tm.isEmpty()) {
				int key = tm.firstKey();
				int id = tm.firstEntry().getValue();
				tm.remove(key);
				Process temp = process[id];
				int rt = Math.min(temp.rt, preEmpt);
				if(rt==temp.rt) {
					temp.ct = i + rt;
					temp.tat = temp.ct - temp.at;
					temp.wt = temp.tat - temp.bt;
				}
				else {
					temp.rt -= rt;
					tm.put(temp.rt, id);
				}
				i += rt;
			}
			else
				i++;
		}
		System.out.println("ID: AT BT CT TAT WT");
		double avg = 0;
		for(int i=0;i<n;i++) {
			System.out.println(process[i]);
			avg += process[i].wt;
		}
		avg/=n;
		System.out.println("Average Waiting Time = " + avg);
	}
}

class Process implements Comparable {
	int id,at,bt,ct,tat,wt,rt;
	
	public Process(int id,int x, int y) {
		this.id = id;
		this.at = x;
		this.bt = y;
		this.rt = this.bt;
	}

	@Override
	public int compareTo(Object o) {
		Process p = (Process)o;
		if(((Integer)at).compareTo(p.at)==0) {
			if(((Integer)bt).compareTo(p.bt)==0)
				return ((Integer)id).compareTo(p.id);
			return ((Integer)bt).compareTo(p.bt);
		}
		return ((Integer)at).compareTo(p.at);
	}
	
	public String toString() {
		return (id + ": " + at + " " + bt + " " + ct + " " + tat + " " + wt);
	}
}
