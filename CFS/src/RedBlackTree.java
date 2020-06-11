import processing.core.*;

public class RedBlackTree {

    private final int RED = 0;
    private final int BLACK = 1;
    public static int NodeCount=0;

    PApplet pApplet;
    
    public class Node {
 
        public Process process = null;
        int  color = BLACK;
        Node left = nil, right = nil, parent = nil;

        Node(Process p) {
            this.process = p;
            NodeCount++;
        } 
    }

    private final Node nil = new Node(null); 
    private Node root = nil;
    public void printRBtree()
    {    	
    	Node rootRBtree=root;
    	printTree(rootRBtree);
    	drawNode(root, 1, 1);
    }
    
    
    public void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        System.out.print("P"+node.process.processId+" "+node.process.vruntime+((node.color==RED)?"R":"B ")+"  ");
        printTree(node.right);
    }

    private Node findNode(Node findNode, Node node) {
        
    	if (root== nil) {
            return null;
        }
    	
        if (findNode.process.vruntime < node.process.vruntime) {
            if (node.left != nil) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.process.vruntime  > node.process.vruntime ) {
            if (node.right != nil) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.process.vruntime  == node.process.vruntime ) {
            return node;
        }
        return null;
    }

    public void insert(Process key) {
    	
    	Node node=new Node(key);
        Node temp = root;
        if (root == nil) {
            root = node;
            node.color = BLACK;
            node.parent = nil;
        } 
        else {
            node.color = RED;
            while (true) {
                if (node.process.vruntime < temp.process.vruntime ) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
               } else if (node.process.vruntime  >= temp.process.vruntime ) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
        
    }

    private void fixTree(Node node) {
        while (node.parent.color == RED) {
            Node uncle = nil;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;
                    
                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } 
                if (node == node.parent.right) {
                    node = node.parent;
                    rotateLeft(node);
                } 
                node.parent.color = BLACK;
                node.parent.parent.color = RED; 
                
                rotateRight(node.parent.parent);
            } else {
            	
                uncle = node.parent.parent.left;
                 if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    void rotateLeft(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) { 
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    void rotateRight(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {//Need to rotate root
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

    void deleteTree(){
        root = nil;
    }
    
    void transplant(Node target, Node with){ 
          if(target.parent == nil){
              root = with;
          }else if(target == target.parent.left){
              target.parent.left = with;
          }else
              target.parent.right = with;
          with.parent = target.parent;
    }
    
    private Node findLeftmost()
    {
    	if(root!=nil)
    	{
    	Node theNode=root;
    	while(theNode.left!=nil)
    		theNode=theNode.left;
    	    return theNode;
		}
    	else
    	{   System.out.println("Empty!!!");
    		return null;
    		}
    	
    }
    
    
    public Node delete(){
    	
    	Node z = findLeftmost();
    	
    	if(z==null){System.out.println("IT IS NULL");}
        if((z = findNode(z, root))==null)
        return null;
        Node x;
        Node y = z;
        int y_original_color = y.color;
        
        if(z.left == nil){
            x = z.right;  
            transplant(z, z.right);  
        }else if(z.right == nil){
            x = z.left;
            transplant(z, z.left); 
        }else{
            y = treeMinimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color; 
        }
        if(y_original_color==BLACK)
            deleteFixup(x);
        NodeCount--;
        return z;
        
    }
    
    void deleteFixup(Node x){
        while(x!=root && x.color == BLACK){ 
            if(x == x.parent.left){
                Node w = x.parent.right;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                Node w = x.parent.left;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK; 
    }
    
    Node treeMinimum(Node subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
    
    void drawNode(Node node, int row, int column){
    	   
        if(node != nil){
          int xPosition = (int)(1100 / ((1<<row)+1))*column;
          int yPosition = row * 50;
          
          //Different colors for left and right nodes
          if(node.color==RED)
            pApplet.fill(255,0,0);  //right node is lighter
          else
        	  pApplet.fill(0);  //left node is darker
          //draw node
          pApplet.ellipse(xPosition, yPosition,  30, 30); 
          
          //Draw texxt
          pApplet.fill(255);
          pApplet.text(""+node.process.processId + "", xPosition-5, row*50+5);
          
          System.out.println("drew at: " + xPosition + " " + (row * 100) + " ");
          
          drawConnector(node.left, xPosition, yPosition, row+1, column*2-1);
          drawConnector(node.right, xPosition, yPosition, row+1, column*2);
          drawNode(node.left, row+1, column*2-1);
          drawNode(node.right, row+1, column*2);
        }
    }

    //Draws connector between x, y and node... x, y are for parent node location
    void drawConnector(Node node, int x, int y, int row, int column){
      
      //Nothing to do here if node is null
      if(node == nil) return;
          
      // padding = (int)(1100 / (pow(2, node.left.data.position.row)+1));
      int x2 = (int)(1100 / ((1<<row)+1))*column;
      int y2 = row * 50;
      
      //The +10 is for putting the line down lower on the node
      pApplet.line(x, y+15, x2, y2);
    }

    
}