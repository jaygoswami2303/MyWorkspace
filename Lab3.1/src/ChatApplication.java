import java.util.Scanner;

public class ChatApplication
{
	public static void main(String[] args)
	{
		ChatRoom cr = new ChatRoom();
		Scanner sc = new Scanner(System.in);
		String s= sc.nextLine();
		String nextLine[] = s.split(" ");
		User u[] = new User[nextLine.length];
		for(int i=0;i<nextLine.length;i++)
			u[i] = new User(nextLine[i],cr);
		while(true)
		{
			s=sc.nextLine();
			nextLine=s.split(" ");
			if(nextLine.length==1)
			{
				toUser(u,nextLine[0]).printLog();
				break;
			}
			String message = nextLine[2];
			for(int i=3;i<nextLine.length;i++)
				message+=" " + nextLine[i];
			String sentUser[] =nextLine[1].split(":");
			toUser(u,nextLine[0]).sendMessage(toUser(u,sentUser[0]), message);
		}
		sc.close();
	}
	
	public static User toUser(User u[],String name)
	{
		int i;
		for(i=0;i<u.length;i++)
		{
			if(u[i].name.equals(name))
				break;
		}
		return u[i];
	}
}
