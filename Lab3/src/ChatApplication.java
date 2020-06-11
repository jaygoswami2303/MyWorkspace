import java.util.Scanner;

public class ChatApplication
{
	private static Scanner input;

	public static void main(String[] args)
	{
		ChatRoom c1= new ChatRoom();
		
		User Ed = new User("Ed",c1);
		User Edd = new User("Edd",c1);
		User Eddy = new User("Eddy",c1);
		
		input = new Scanner(System.in);
		
		String s1,s2,m;
		User u1,u2;
		while(true)
		{
			s1=input.next();
			if(s1.equals("Ed"))
				u1=Ed;
			else if(s1.equals("Edd"))
				u1=Edd;
			else
				u1=Eddy;
			s2=input.next();
			Scanner scan = new Scanner(s2);
			if(scan.hasNextLine())
			{
				s2=input.next();
				m=input.next();
				if(s2.equals("Ed"))
					u2=Ed;
				else if(s2.equals("Edd"))
					u2=Edd;
				else
					u2=Eddy;
				u1.writeMessage('s', u2, m);
				u2.writeMessage('r', u1, m);
			}
			else
			{
				u1.printLog();
				break;
			}
			scan.close();
		}
	}
}
