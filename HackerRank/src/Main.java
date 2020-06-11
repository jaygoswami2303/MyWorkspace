import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static ArrayList<Integer>[] ar;
	public static boolean visited[];
	public static int dis[];
	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 int n = sc.nextInt();
		 
		 Edge e[] = new Edge[n-1];
		 
		 for(int i=0;i<n-1;i++) {
			 e[i] = new Edge(sc.nextInt()-1,sc.nextInt()-1);
		 }
		 sc.close();
		 
		 int ans = 0;
		 for(int i=0;i<n-1;i++) {
			 ar = new ArrayList[n];
			 for(int j=0;j<n;j++)
				 ar[j] = new ArrayList<Integer>();
			 for(int j=0;j<n-1;j++) {
				 if(j!=i) {
					 ar[e[j].u].add(e[j].v);
					 ar[e[j].v].add(e[j].u);
				 }
			 }
			 int u = e[i].u;
			 int v = e[i].v;
			 visited = new boolean[n];
			 dis = new int[n];
			 for(int j=0;j<n;j++) {
				 if(j!=u)
					 dis[j] = -1;
			 }
			 dfs(u);
			 int z = u;
			 for(int j=0;j<n;j++) {
				 if(dis[j]>dis[z])
					 z=j;
			 }
			 visited = new boolean[n];
			 dis = new int[n];
			 for(int j=0;j<n;j++) {
				 if(j!=z)
					 dis[j] = -1;
			 }
			 dfs(z);
			 int l1 = 0;
			 for(int j=0;j<n;j++) {
				 if(dis[j]>l1)
					 l1 = dis[j];
			 }
			 
			 visited = new boolean[n];
			 dis = new int[n];
			 for(int j=0;j<n;j++) {
				 if(j!=v)
					 dis[j] = -1;
			 }
			 dfs(v);
			 z = v;
			 for(int j=0;j<n;j++) {
				 if(dis[j]>dis[z])
					 z=j;
			 }
			 visited = new boolean[n];
			 dis = new int[n];
			 for(int j=0;j<n;j++) {
				 if(j!=z)
					 dis[j] = -1;
			 }
			 dfs(z);
			 int l2 = 0;
			 for(int j=0;j<n;j++) {
				 if(dis[j]>l2)
					 l2 = dis[j];
			 }
			 
			 if(l1*l2>ans) {
				 ans = l1*l2;
			 }
		 }
		 System.out.print(ans);
	}
	
	public static void dfs(int node) {
		visited[node] = true;
		for(int i=0;i<ar[node].size();i++) {
			if(!visited[ar[node].get(i)]) {
				dis[ar[node].get(i)] = dis[node] + 1;
				dfs(ar[node].get(i));	
			}
		}
	}
}

class Edge {
	int u;
	int v;
	
	Edge(int u,int v) {
		this.u = u;
		this.v = v;
	}
}

