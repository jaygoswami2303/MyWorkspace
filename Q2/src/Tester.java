import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		MassMolecule m = new MassMolecule();
		Scanner scan = new Scanner(new File("composition_data"));
		while(scan.hasNextLine())
		{
			String s = scan.nextLine();
			String mass = m.getMass(s)[0];
			System.out.println("The mass of " + s + " is " + mass);
		}
		scan.close();
	}
}