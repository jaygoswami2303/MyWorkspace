package Lab1;

import java.util.*;
import java.io.*;

public class SJF {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
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
				tm.put(process[index].bt, process[index].id);
				hs.remove(process[index]);
				tmAt.remove(tmAt.firstKey());
			}
			if(!tm.isEmpty()) {
				int key = tm.firstKey();
				int id = tm.firstEntry().getValue();
				tm.remove(key);
				Process temp = process[id];
				temp.ct = i + temp.bt;
				temp.tat = temp.ct - temp.at;
				temp.wt = temp.tat - temp.bt;
				i += temp.bt;
			}
			else {
				i++;
			}
		}
		
		System.out.println("ID: AT BT CT TAT WT");
		double avg = 0;
		double avgt = 0;
		for(int i=0;i<n;i++) {
			System.out.println(process[i]);
			avg += process[i].wt;
			avgt += process[i].tat;
		}
		avg/=n;
		avgt/=n;
		System.out.println("Average Waiting Time = " + avg);
		System.out.println("Average Turn Around Time = " + avgt);
	}
}

class Process implements Comparable {
	int id,at,bt,ct,tat,wt;
	
	public Process(int id,int x, int y) {
		this.id = id;
		this.at = x;
		this.bt = y;
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
