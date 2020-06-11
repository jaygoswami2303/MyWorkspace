import java.util.Random;

public class User
{
	private final static int MAX_LOGS=750;
	private String name;
	private String receivedChatLogs[][] = new String[MAX_LOGS][2];
	private String sentChatLogs[][] = new String[MAX_LOGS][2];
	private ChatRoom assignedChatRoom;
	private final static int MAX_MESSAGE_LENGTH=100;
	private int totalReceived = 0;
	private int totalSent=0;
	
	User(String n,ChatRoom c)
	{
		this.name=n;
		this.assignedChatRoom=c;
	}
	
	public void writeMessage()
	{
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String message = "["+name+"] : ";
        Random messageRandomization = new Random();
        int messageLength = 1+messageRandomization.nextInt(MAX_MESSAGE_LENGTH);
        for (int i = 0; i < messageLength; i++)
        {
            message += alphabet.charAt(messageRandomization.nextInt(alphabet.length()));
        }
        this.assignedChatRoom.newMessage(message);
    }

	public void writeMessage(char c,User u,String message)
	{
		if(c=='r')
		{
			this.receivedChatLogs[totalReceived][0]=u.name;
			this.receivedChatLogs[totalReceived][1]=message;
			totalReceived++;
		}
		else
		{
			this.sentChatLogs[totalReceived][0]=u.name;
			this.sentChatLogs[totalReceived][1]=message;
			totalSent++;
		}
	}
	
	public void printLog()
	{
		System.out.println(this.name + "'s sent messages:");
		for(int i=0;i<totalSent;i++)
			System.out.println(sentChatLogs[i][0] +" : " + sentChatLogs[i][1]);
		System.out.println(this.name + "'s received messages:");
		for(int i=0;i<totalReceived;i++)
			System.out.println(receivedChatLogs[i][0] +" : " + receivedChatLogs[i][1]);
	}
}
