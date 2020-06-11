import java.util.*;
import java.lang.*;
import java.io.*;

class Television
{
	int brightness,contrast,saturation;
	
	void SwitchOnTV()
	{
		System.out.println("TV is switched ON");
		System.out.print("Brightness = ");
		System.out.println(brightness);
		System.out.print("Contrast=");
		System.out.println(contrast);
		System.out.print("Saturation");
		System.out.println(saturation);		
	}
	
	void SwitchOffTV()
	{
		System.out.println("TV is switched OFF");
	}
	
	public static void main(String[] args) throws java.lang.Exception
	{
		Television Panasonic = new Television();
		Panasonic.brightness=95;
		Panasonic.contrast=78;
		Panasonic.saturation=65;
		
		Television Sony = new Television();
		Sony.brightness=95;
		Sony.contrast=78;
		Sony.saturation=65;
		
		Television Toshiba = new Television();
		Toshiba.brightness=95;
		Toshiba.contrast=78;
		Toshiba.saturation=65;
		
		Panasonic.SwitchOnTV();
		Panasonic.SwitchOffTV();
		
		Sony.SwitchOnTV();
		Sony.SwitchOffTV();
		
		Toshiba.SwitchOnTV();
		Toshiba.SwitchOffTV();
	}
}