public class Process implements Comparable<Process>{
	
	public int priority;
	public int processId;
	public long execTime;
	public long timeInCPU;
	public long waitTime;
	public long arrivalTime;
	public long vruntime;
	public long startTime;
	public long turnAroundTime;
	
	public int row;
	public int column;
	
	public Process(int newId,long newArrivalTime, long newExecTime, int newPriority){
		
		timeInCPU=0;
		processId=newId;
		execTime=newExecTime;
		arrivalTime=newArrivalTime;
		priority = newPriority;
		waitTime=arrivalTime;
		vruntime=arrivalTime;	
	}

	@Override
	public int compareTo(Process p) {
		if(Long.compare(arrivalTime, p.arrivalTime)==0)
			return Integer.compare(processId, p.processId);
		return Long.compare(arrivalTime, p.arrivalTime);
	}
	
	
}
