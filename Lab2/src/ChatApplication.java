public class ChatApplication
{
	public static void main(String[] args)
	{
		ChatRoom cr = new ChatRoom();
		
		User u1 = new User(cr);
		User u2 = new User(cr);
		
		int conversationLength=10;
		
		for(int i=0;i<conversationLength;i++)
		{
			u1.writeMessage();
			u2.writeMessage();
		}
		
		cr.printLog();
	}

}
