import processing.core.*;

public class Simulator extends PApplet {
	
	Ant ant;
	int w = 24;
	int n = 17;
	int t = 50;
	
    public void setup() {
    	ant = new Ant(this, w, n, t);
    }
    
    public void settings() {
    	size(w*(n+2),w*(n+2));
    }
    
    public void draw() {
    	background(255);
    	ant.generate();
    	delay(1000);
    	if(ant.display()) {
    		exit();
    		return;
    	}
    }
    
}