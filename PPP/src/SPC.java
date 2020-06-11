import java.sql.*;
import java.util.*;

public class SPC {
	private String emailID;
	private PPPUser spcuname;
	private static SPC currentSPC = null;

	public PPPUser getSpcuname() {
		return spcuname;
	}

	public void setSpcuname(PPPUser spcuname) {
		this.spcuname = spcuname;
	}

	public SPC() {
	}
	
	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public static SPC Register(Connection c, Scanner s) {
		PPPUser user = PPPUser.Register(c, s);
		SPC spc;
		try {
			spc = new SPC();
			spc.setSpcuname(user);
			System.out.println("enter email address");
			String str=s.nextLine();
			spc.setEmailID(str);

			Statement stmt = c.createStatement();
	        String sql = "INSERT INTO spc (email_id,spcuname,spcpasswd) "
	           + "VALUES ("+ spc.emailID + "', '" + spc.getSpcuname().getUname() + "', '" + spc.getSpcuname().getPasswd() + "');";
	        stmt.executeUpdate(sql);
	
	        stmt.close();
	        c.commit();
	        System.out.println("SPC Member registered successfully");
		} catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         spc = null;
	      }
		return spc;
	}
	
	public static SPC logIn(Connection c, Scanner sc) {
		SPC spc = null;
		try {			
			while(true) {
				System.out.println("enter username");
				String str = sc.nextLine();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM spc WHERE spcuname = '" + str +"';" );
		        if ( rs.next() ) {
		            String  emailId= rs.getString("email_id");
		            PPPUser user = PPPUser.logIn(c, str);
		            String password = user.getPasswd();
		            System.out.println("enter passwd");
					String pass = sc.nextLine();
					while(!pass.equals(password)) {
						System.out.println("Incorrect ! enter correct passwd ");
						pass = sc.nextLine();
					}
					spc = new SPC();
					spc.setEmailID(emailId);
					spc.setSpcuname(user);
		            break;
		         }
		        else {
		        	System.out.println("SPC Member doen't exist");
		        }
		         rs.close();
		         stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
		return spc;
	}
	
	public static void spcHome(Connection c, Scanner sc) {
		System.out.println("Welcome SPC member!");
		boolean loop = true;
		boolean loggedIn = false;
		while(loop) {
			if(currentSPC != null)
				loggedIn = true;
			if(loggedIn)
				System.out.println("Enter the command : 1) Get Student profile 0) Log Out");
			else
				System.out.println("Enter the command : 1) Register 2) Login 0) Exit");
    	  String str = sc.nextLine();
    	  int choice = Integer.parseInt(str);
    	  if(loggedIn) {
	    	  switch(choice) {
		    	  case 1:
		    		  if(currentSPC==null) {
		    			  System.out.println("Please login first");
		    			  break;
		    		  }
		    		  displayStudentProfile(c, sc);
		    		  break;
		    	  case 0:
		    		currentSPC = null;
		    		break;
		    	  default:
		    		  System.out.println("Enter the correct choice");
	    	  }
    	  }
    	  else {
    		  switch(choice) {
	    	  case 1:
	    		  currentSPC = SPC.Register(c, sc);
	    		  break;
	    	  case 2:
	    		  currentSPC = SPC.logIn(c, sc);
	    		  break;
	    	  case 0:
	    		loop = false;
	    		break;
	    	  default:
	    		  System.out.println("Enter the correct choice");
    	  }
    	  }
      }
	}
	
	private static void displayStudentProfile(Connection c, Scanner sc) {
		System.out.println("Enter the roll_no of student to see their profile");
		String str = sc.nextLine();
		Student student = Student.getStudentProfile(c, str);
		System.out.println("Student Details:");
		System.out.println("\t Username: " + student.getSuname().getUname());
		System.out.println("\t Roll Number: " + student.getRoll_no());
		System.out.println("\t Email ID: " + student.getEmailID());
		System.out.println("\t Branch: " + student.getBranch());
		System.out.println("\t Batch: " + student.getBatch());
		System.out.println();
	}
	
}
