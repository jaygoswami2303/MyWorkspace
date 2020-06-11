public class User
{
	private static final int MAX_CHAT_LOGS=75;
	public String name;
	private String receivedChats[] = new String[MAX_CHAT_LOGS];
	private int receivedTotalChats=0;
	private String sentChats[] = new String[MAX_CHAT_LOGS];
	private int sentTotalChats=0;
	private ChatRoom assignedChatRoom;
	
	public User(String name, ChatRoom cr)
	{
		this.name=name;
		this.assignedChatRoom=cr;
	}
	private void receiveMessage(User u,String message)
	{
		this.receivedChats[receivedTotalChats++]=u.name+": " +message;
		this.assignedChatRoom.receiveUserMessage(u, this, message);
	}
	
	public void sendMessage(User u,String message)
	{
		this.sentChats[sentTotalChats++]=u.name +": " +message;
		u.receiveMessage(this, message);
	}
	
	public void printLog()
	{
		System.out.println(this.name + "'s sent messages:");
		for(int i=0;i<sentTotalChats;i++)
			System.out.println(sentChats[i]);
		System.out.println();
		System.out.println(this.name + "'s received messages:");
		for(int i=0;i<receivedTotalChats;i++)
			System.out.println(receivedChats[i]);
	}
}
