public class ChatRoom
{
	private static final int MAX_CHAT_LOGS=10;
	private String chatLogs[] = new String[MAX_CHAT_LOGS];
	private int totalChats=0;
	private String previousLogs[] = new String[MAX_CHAT_LOGS];
	private int previousTotalChats=0;
	private int flag=0;
	
	public void receiveUserMessage(String message)
	{
		if(totalChats==10)
		{
			flag=1;
			totalChats=0;
		}
		if(flag==1)
		{
			previousLogs[previousTotalChats++]=chatLogs[totalChats];
		}
		chatLogs[totalChats++]=message;
	}
	
	public void printLog()
	{
		if(flag==1)
		{
			for(int i=0;i<previousTotalChats;i++)
				System.out.println(previousLogs[i]);
			for(int i=totalChats;i<MAX_CHAT_LOGS;i++)
				System.out.println(chatLogs[i]);
		}
		for(int i=0;i<totalChats;i++)
			System.out.println(chatLogs[i]);
	}
}
