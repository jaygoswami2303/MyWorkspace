import java.sql.*;
import java.util.*;

public class Main {
   public static void main(String args[]) {
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://10.100.71.21:5432/201501451?currentSchema=ppp",
            "201501451", "201501451");
         c.setAutoCommit(false);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
      Scanner sc = new Scanner(System.in);
      boolean loop = true;
	  while(loop) {
    	  System.out.println("Who are you? 1) Student 2) SPC Member 0) Exit");
    	  String str = sc.nextLine();
    	  int choice = Integer.parseInt(str);
    	  switch(choice) {
	    	  case 1:
	    		  Student.studentHome(c, sc);
	    		  break;
	    	  case 2:
	    		  SPC.spcHome(c, sc);
	    		  break;
	    	  case 0:
	    		loop = false;
	    		break;
	    	  default:
	    		  System.out.println("Enter the correct choice");
    	  }
      }
      sc.close();
      System.out.println("Thank You for using Placement Preparation Portal");
      try {
    	  c.close();
    	  System.out.println("Database connection closed");
      } catch(Exception e) {
    	  e.printStackTrace();
          System.err.println(e.getClass().getName()+": "+e.getMessage());
          System.exit(0); 
      }
   }
}