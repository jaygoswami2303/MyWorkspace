import java.util.*;

public class Scheduler {
	
	private int quantumTime = 2;
	Process p;
	public static long CLOCK=0;
	public static long CLOCK2=0;
	int n;
	
	long totalWaitTime=0;
	long totalTurnAroundTime=0;
	long timeKeeper=0;
	
	public void addProcess(RedBlackTree rt, Queue<Process> processes) {
		while(!processes.isEmpty() && processes.peek().arrivalTime<=CLOCK2) {
			rt.insert(processes.remove());
		}
	}

	public void scheduleRBTree(RedBlackTree rt)
	{	
		if(RedBlackTree.NodeCount>1){
			rt.printRBtree();
			long start = System.nanoTime();
			p=rt.delete().process;
				
			
			if(p.execTime>quantumTime)
			{
				p.vruntime=p.vruntime+p.priority*quantumTime;
				p.timeInCPU=p.timeInCPU+quantumTime;
				p.execTime=p.execTime-quantumTime;
				CLOCK2=CLOCK2+quantumTime;
				p.waitTime=CLOCK2- p.arrivalTime- p.timeInCPU; 
				if(p.execTime>0)
				{
					rt.insert(p);
				}
				else
				{
					
					totalWaitTime+=p.waitTime;
					p.turnAroundTime=CLOCK2-p.arrivalTime;
					totalTurnAroundTime+=p.turnAroundTime;
				}
				
			}
			else
			{
				p.timeInCPU=p.timeInCPU+p.execTime;
				CLOCK2=CLOCK2+p.execTime;
				p.waitTime=CLOCK2-p.arrivalTime-p.timeInCPU;  
				p.execTime=0;	
				totalWaitTime+=p.waitTime;
				p.turnAroundTime=CLOCK2-p.arrivalTime;
				totalTurnAroundTime+=p.turnAroundTime;
				

			}
			
			long end = System.nanoTime();
			timeKeeper+=(end-start);
		}
		else {
			CLOCK2 = CLOCK2 + quantumTime;
		}
		
	}
	
	public void printStatistics() {
		System.out.println("\n\nScheduling using red black tree data structure...\n");
		System.out.println("\nScheduling Metric/Unfairness Measure--Time In Processor--");
		float thr=((float)n*100000)/((float)timeKeeper);
		long timeUnit=timeKeeper/CLOCK2;
		System.out.println("\nCOMPLETELY FAIR SCHEDULING USING RED BLACK TREES- PERFORMANCE METRICS ");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\n1.Total Number of inputs :"+n);
		System.out.println("\n2.Total Running Time: "+ timeKeeper+ " nano seconds");
		System.out.println("\n3.Running time per process:" +timeKeeper/n+ " nano seconds");
		System.out.println("\n4.Total Wait Time :"+totalWaitTime*timeUnit+" nano seconds");
		System.out.println("\n5.Average Wait Time :"+(totalWaitTime/n)*timeUnit+" nano seconds");
		System.out.println("\n6.Total turn around time: " +totalTurnAroundTime*timeUnit+" nano seconds");
		System.out.println("\n7.Average turn around time: " +(totalTurnAroundTime/n)*timeUnit+" nano seconds");
		System.out.printf("\n8.Throughput: %.2f tasks/millisecond",thr);
	}

}
