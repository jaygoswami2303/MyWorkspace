import processing.core.*;
import java.io.*;
import java.util.*;

public class Simulator extends PApplet {

	Scheduler sc;
	RedBlackTree rbt;
	Queue<Process> processes;
	PFont font;
	
    public void setup() {
    	
    	rbt=new RedBlackTree();
    	rbt.pApplet = this;
    	
    	TreeSet<Process> tm = new TreeSet<Process>();
    	processes = new LinkedList<Process>();
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("Input10000.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int n=scanner.nextInt();
		int period=scanner.nextInt();
		System.out.println("Number of inputs="+n);
		System.out.println("Quantum period= "+period);
		for(int j=0;j<n;j++)
		{
			int id = scanner.nextInt();
			int startTime = scanner.nextInt();
			int execTime = scanner.nextInt();
			int priority = scanner.nextInt();
			Process p = new Process(id, startTime, execTime, priority);
			tm.add(p);
		}
					
		scanner.close();			
		
		Iterator<Process> it = tm.iterator();
		while(it.hasNext())
			processes.add(it.next());
		
		sc=new Scheduler();
		sc.n = n;
    	
    	background(220, 240, 230);
    	  
    	fill(255);
    	font = createFont("Arial", 14, true);
    	textFont(font, 14);
    	rectMode(CENTER);
    	frameRate(5);
    }
    
    public void settings() {
    	size(1200, 800);
    	
    }
    
    public void draw() {
    	background(220, 240, 230);
    	System.out.println(RedBlackTree.NodeCount);
    	sc.addProcess(rbt, processes);
    	if(processes.isEmpty() && RedBlackTree.NodeCount==1) {
    		sc.printStatistics();
    		exit();
    		return;
    	}
    	sc.scheduleRBTree(rbt);
    	delay(2000);
    }
}