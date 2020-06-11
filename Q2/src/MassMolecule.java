import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MassMolecule
{
	private Molecule molecules[];
	
	public MassMolecule() throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("molecule weight data"));
		int length = scan.nextInt();
		scan.nextLine();
		molecules = new Molecule[length];
		for(int i=0;i<length;i++)
		{
			String s = scan.nextLine();
			String ss[] = s.split(" ");
			molecules[i] = new Molecule(ss[0].charAt(0),ss[1]);
		}
		scan.close();
	}

	private Molecule getMolecule(char n)
	{
		int i;
		for(i=0;i<molecules.length;i++)
		{
			if(molecules[i].getName()==n)
				return molecules[i];
		}
		return null;
	}
	
	public String[] getMass(String m)
	{
		String mass[] = new String[2];
		mass[0]="0";
		for(int i=0;i<m.length();i++)
		{
			if(m.charAt(i)=='(')
			{
				String rMass[] = getMass(m.substring(i+1));
				mass[0]=string.Add(mass[0],rMass[0]);
				i=i+Integer.parseInt(rMass[1]);
			}
			else if(m.charAt(i)==')')
			{
				String mass1 = new String(mass[0]);
				if(m.length()>i+1 && m.charAt(i+1)>49 && m.charAt(i+1)<58)
				{
					for(int k=m.charAt(i+1)-48;k>1;k--)
						mass[0] = string.Add(mass[0],mass1);
					mass[1]=Integer.toString(i+2);
				}
				else
					mass[1]=Integer.toString(i+1);				
				return mass;
			}
			else
			{
				Molecule a = getMolecule(m.charAt(i));
				mass[0] = string.Add(mass[0],a.getMass());
			}
		}
		return mass;
	}
}