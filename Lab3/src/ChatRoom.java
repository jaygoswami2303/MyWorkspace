public class ChatRoom
{
	private final static int MAX_CHAT_LOGS = 1000;
	private String chatLogs[] = new String[MAX_CHAT_LOGS];
	private int totalLogs = 0;
	
	public void newMessage(String m)
	{
		this.chatLogs[totalLogs]=m;
		totalLogs++;
	}
	
	public void printLogs()
	{
		for(int i=0;i<totalLogs;i++)
			System.out.println(chatLogs[i]);
	}
}
