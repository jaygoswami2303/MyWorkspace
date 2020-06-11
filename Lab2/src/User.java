import java.util.Random;

public class User
{
	private static int autoID=1;
	private int userID;
	private static final int MAX_MESSAGE_LENGTH=100;
	private ChatRoom assignedChatRoom;
	private static int count=1;
	public User(ChatRoom cr)
	{
		this.userID=autoID++;
		this.assignedChatRoom=cr;
	}
	
	public void writeMessage()
	{
		String alphabets = "abcdefghijklmnopqrsstuvwxyz";
		String message = count + " [u" + userID + "]";
		count++;
		Random random = new Random();
		int messageLength = 1 + random.nextInt(MAX_MESSAGE_LENGTH);
		for(int i=0;i<messageLength;i++)
			message += alphabets.charAt(random.nextInt(alphabets.length()));
		this.assignedChatRoom.receiveUserMessage(message);
	}
}
