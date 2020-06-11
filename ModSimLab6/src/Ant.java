import java.util.*;
import processing.core.*;

public class Ant {

	int w;
	
	int antGrid[][];
	double pherGrid[][];
	int previousAntGrid[][];
	double previousPherGrid[][];
	ArrayList<int [][]> antGrids;
	ArrayList<double [][]> pherGrids;
	
	double MAXPHER = 50.0;
	int EVAPORATE = 1;
	int THRESHOLD = 0;
	int DEPOSIT = 2;
	
	int n;
	double probAnt = 0.1;
	double diffusionRate = 0.01;
	int t;
	
	PApplet pApplet;
	
	public Ant(PApplet p, int width, int number, int time) {
		
		pApplet = p;
		w = width;
		n = number;
		t = time;
		
    	initAntGrid(n, probAnt);
    	initPherGrid(n);
    	
    	previousAntGrid = new int[n+2][n+2];
    	previousPherGrid = new double[n+2][n+2];
    	
    	antGrids = new ArrayList<int [][]>();
    	pherGrids = new ArrayList<double [][]>();
	}
	
	public void initAntGrid(int n, double probAnt) {
    	antGrid = new int[n+2][n+2];
        for(int i=0;i<n+2;i++) {
            antGrid[i][0] = 6;
            antGrid[0][i] = 6;
            antGrid[i][n+1] = 6;
            antGrid[n+1][i] = 6;
        }
        Random random = new Random();
        for(int i=1;i<n+1;i++) {
            for(int j=1;j<n+1;j++) {
                if(random.nextDouble()<probAnt)
                    antGrid[i][j] = random.nextInt(4)+1;
            }
        }
    }
    
    public void initPherGrid(int n) {
    	pherGrid = new double[n+2][n+2];
        for(int i=0;i<n+2;i++) {
        	pherGrid[0][i] = -0.01;
            pherGrid[i][0] = -0.01;
            pherGrid[n+1][i] = -0.01;
            pherGrid[i][n+1] = -0.01;
        }
        int t = (int) (Math.ceil(n/2)+1);
        for(int i=1;i<n+1;i++)
            pherGrid[t][i] = MAXPHER*(i-1)/n;
    }
    
    public void savePrevious() {
    	for(int i = 0; i < n+2; i++) {
    	    previousAntGrid[i] = antGrid[i].clone();
    	    previousPherGrid[i] = pherGrid[i].clone();
    	}
    }
    
    public void applySenseExtended() {
        for(int i=1;i<n+1;i++) {
            for(int j=1;j<n+1;j++) {
                if(previousAntGrid[i][j]!=0)
                    antGrid[i][j] = sense(previousAntGrid[i][j], previousAntGrid[i-1][j], previousAntGrid[i][j+1], previousAntGrid[i+1][j], previousAntGrid[i][j-1], previousPherGrid[i-1][j], previousPherGrid[i][j+1], previousPherGrid[i+1][j], previousPherGrid[i][j-1]);
            }
        }
        savePrevious();
    }
    
    public int sense(int site, int na, int ea, int sa, int wa, double np, double ep, double sp, double wp) {
    	double lst[] = {np, ep, sp ,wp};
	    if(site!=5)
	        lst[site-1] = -2;
	    if(na!=0)
	        lst[0] = -2;
	    if(ea!=0)
	        lst[1] = -2;
	    if(sa!=0)
	        lst[2] = -2;
	    if(wa!=0)
	        lst[3] = -2;
	    double mx = Math.max(lst[0], Math.max(lst[1], Math.max(lst[2], lst[3])));
	    int dir;
	    if(mx<0)
	        dir = 5;
	    else {
	        ArrayList<Integer> posList = new ArrayList<Integer>();
	        for(int i=0;i<lst.length;i++) {
	            if(lst[i]==mx)
	                posList.add(i);
	        }
	        Random random = new Random();
	        dir = posList.get(random.nextInt(posList.size()));
	    }
	    return dir;
	}
    
    public void walk() {
    	for(int i=1;i<n+1;i++) {
    		for(int j=1;j<n+1;j++) {
    			if(previousAntGrid[i][j]==0)
    				pherGrid[i][j]= Math.max(0, pherGrid[i][j]-EVAPORATE);
            
    			if(previousAntGrid[i][j]==1) {
	                if(antGrid[i-1][j]==0) {
	                    if(pherGrid[i][j]>THRESHOLD)
	                        pherGrid[i][j] = pherGrid[i][j]+DEPOSIT;
	                    antGrid[i][j] = 0;
	                    antGrid[i-1][j] = 3;
	                }
	                else
	                    antGrid[i][j] = 5;
    			}
    			else if(previousAntGrid[i][j]==2) {
    				if(antGrid[i][j+1]==0) {
	                    if(pherGrid[i][j]>THRESHOLD)
	                        pherGrid[i][j] = pherGrid[i][j]+DEPOSIT;
	                    antGrid[i][j] = 0;
	                    antGrid[i][j+1] = 4;
	                }
	                else
	                    antGrid[i][j] = 5;
    			}
    			else if(previousAntGrid[i][j]==3) {
    				if(antGrid[i+1][j]==0) {
	                    if(pherGrid[i][j]>THRESHOLD)
	                        pherGrid[i][j] = pherGrid[i][j]+DEPOSIT;
	                    antGrid[i][j] = 0;
	                    antGrid[i+1][j] = 1;
	                }
	                else
	                    antGrid[i][j] = 5;
    			}
    			else if(previousAntGrid[i][j]==4) {
    				if(antGrid[i][j-1]==0) {
	                    if(pherGrid[i][j]>THRESHOLD)
	                        pherGrid[i][j] = pherGrid[i][j]+DEPOSIT;
	                    antGrid[i][j] = 0;
	                    antGrid[i][j-1] = 2;
	                }
	                else
	                    antGrid[i][j] = 5;
    			}
    		}
    	}
    	savePrevious();
    }

    public void applyDiffusionExtended() {
	    for(int i=1;i<n+1;i++) {
	        for(int j=1;j<n+1;j++) {
	            pherGrid[i][j] = diffusion_pher(previousPherGrid[i][j], previousPherGrid[i-1][j], previousPherGrid[i-1][j+1], previousPherGrid[i][j+1], previousPherGrid[i+1][j+1], previousPherGrid[i+1][j], previousPherGrid[i+1][j-1], previousPherGrid[i][j-1], previousPherGrid[i-1][j-1]);
	        }
	    }
	    savePrevious();
    }
    
    public double diffusion_pher(double site, double N, double NE, double E, double SE, double S, double SW, double W, double NW) {
    	return (1 - 8*diffusionRate)*site + diffusionRate*(N + NE + E + SE + S + SW + W + NW);
    }
    
    public void generate() {
    	savePrevious();
    	applySenseExtended();
    	walk();
    	applyDiffusionExtended();
    	antGrids.add(antGrid);
    	pherGrids.add(pherGrid);
    }
    
    public boolean display() {
    	double max = pherGrid[0][0];
    	for(int i=0;i<n+2;i++) {
    		for(int j=0;j<n+2;j++) {
    			max = Math.max(max, pherGrid[i][j]);
    		}
    	}
    	for(int i=0;i<n+2;i++) {
    		for(int j=0;j<n+2;j++) {
    			if(pherGrid[i][j]>=0) {
    				pApplet.fill((int)(255*(1 - pherGrid[i][j]/max)));
	    			pApplet.stroke(255);
	    			pApplet.rect(j*w, i*w, w, w);
    			}
    		}
    	}
    	for(int i=0;i<n+2;i++) {
    		for(int j=0;j<n+2;j++) {
    			if(antGrid[i][j]>=1 && antGrid[i][j]<=5) {
    				pApplet.fill(21, 192, 243);
    				pApplet.stroke(255);
        			pApplet.rect(j*w, i*w, w, w);
    			}
    		}
    	}
    	return antGrids.size()==t;
    }
}
