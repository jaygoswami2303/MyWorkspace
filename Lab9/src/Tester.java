import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws FileNotFoundException
	{
		BST tree = new BST();
		Scanner scan = new Scanner(new File("abc.txt"));
		int length = scan.nextInt();
		scan.nextLine();
		for(int i=0;i<length;i++)
		{
			String nL = scan.nextLine();
			String ss[] = nL.split(" ");
			tree.add(ss[0],Integer.parseInt(ss[1]),Integer.parseInt(ss[2]),Integer.parseInt(ss[3]));
		}
		length = scan.nextInt();
		scan.nextLine();
		for(int i=0;i<length;i++)
		{
			String nL = scan.nextLine();
			String ss[] = nL.split(" ");
			tree.search(Integer.parseInt(ss[0]),Integer.parseInt(ss[1]));
		}
		scan.close();
	}
}