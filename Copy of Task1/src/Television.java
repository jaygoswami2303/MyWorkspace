import java.util.*;
import java.lang.*;
import java.io.*;
class Television
{
	int brightness,contrast;
	String name;
	Television(String n,int b, int c)
	{
		this.name=n;
		this.brightness=b;
		this.contrast=c;
	}
	void SwitchOnTV()
	{
		System.out.println("********" +name +"********");
		System.out.println("TV is switched ON");
		System.out.println(brightness);      //Printing value stored in 'brightness' variable.         
		System.out.println(contrast);      //Printing value stored in 'contrast' variable.
	}
	void SwitchOffTV()        //Method-2 for Television class. This shows the status when TV is OFF.
	{         
		System.out.println("TV is switched OFF");   //Printing message
	}
}   

class FirstJavaClass                    //Main Class (Always required, main function resides in this class)
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Television Samsung = new Television("Samsung",90,70);   //Creaing an Object of Television class
		          //Assigning value to variable for object - 1
				      //Assigning value to variable for object - 1
		
		Television Sony = new Television("Sony",100,85);
		
	    //System.out.println("********SAMSUNG**********");
	    Samsung.SwitchOnTV(); //Calling method of television class via object-1
	    System.out.println("******************");
	    Samsung.SwitchOffTV();      //Calling method of television class via object-1
	    //System.out.println("********SONY**********");
	    Sony.SwitchOnTV();
	    System.out.println("******************");
	    Sony.SwitchOffTV();
	}	
}
