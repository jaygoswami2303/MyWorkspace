public class ChatRoom
{
	private static final int MAX_CHAT_LOGS=100;
	private String chatLogs[] = new String[MAX_CHAT_LOGS];
	private int totalChats=0;
	
	public void receiveUserMessage(User u1,User u2,String message)
	{
		chatLogs[totalChats++]=u1.name + " to " + u2.name + " : " + message;
	}
	
	public void printLog()
	{
		for(int i=0;i<totalChats;i++)
			System.out.println(chatLogs[i]);
	}
}
