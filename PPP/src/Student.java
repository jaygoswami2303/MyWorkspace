import java.sql.*;
import java.util.*;

public class Student {
	private String emailID;
	private PPPUser suname;
	private String roll_no;
	private String batch;
	private String branch;
	private static Student currentStudent = null;
	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public PPPUser getSuname() {
		return suname;
	}

	public void setSuname(PPPUser suname) {
		this.suname = suname;
	}

	public Student() {
	}
	
	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public static Student Register(Connection c, Scanner s) {
		PPPUser user = PPPUser.Register(c, s);
		Student student;
		try {
			student = new Student();
			student.setSuname(user);
			System.out.println("enter your roll_no");
			String str=s.nextLine();
			while(checkRegistered(c,str)){
				System.out.println("entered roll_no is already exists, choose another one");
				str=s.nextLine();
			}
			student.setRoll_no(str);
			System.out.println("enter email address");
			str=s.nextLine();
			student.setEmailID(str);
			System.out.println("enter your batch");
			str=s.nextLine();
			student.setBatch(str);
			System.out.println("enter your branch");
			str=s.nextLine();
			student.setBranch(str);

			Statement stmt = c.createStatement();
	        String sql = "INSERT INTO student (roll_no,email_id,suname,batch,branch) "
	           + "VALUES ("+ student.roll_no + ", '" + student.emailID + "', '" + student.suname.getUname() + "', '" + student.batch + "', '" + student.branch + "');";
	        stmt.executeUpdate(sql);
	
	        stmt.close();
	        c.commit();
	        System.out.println("Student registered successfully");
		} catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         student = null;
	      }
		return student;
	}
	
	public static boolean checkRegistered(Connection c, String roll_no) {
		boolean result = false;
		try {
			Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM student WHERE roll_no = '" +  roll_no + "';");
	         if(rs.next()) {
	        	 result = true;
	         }
	         else {
	        	 result = false;
	         }
	         rs.close();
	         stmt.close();
		} catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	      }
		return result;
	}
	
	public static Student logIn(Connection c, Scanner sc) {
		Student student = null;
		try {			
			while(true) {
				System.out.println("enter roll_no");
				String str = sc.nextLine();
				student = getStudentProfile(c, str);
		        if (student!=null) {
		            String  password = student.getSuname().getPasswd();
		            System.out.println("enter passwd");
					String pass = sc.nextLine();
					while(!pass.equals(password)) {
						System.out.println("Incorrect ! enter correct passwd ");
						pass = sc.nextLine();
					}
		            break;
		         }
			}
		} catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
		return student;
	}
	
	public static void studentHome(Connection c, Scanner sc) {
		System.out.println("Welcome student!");
		boolean loop = true;
		boolean loggedIn;
		while(loop) {
			loggedIn = (currentStudent != null);
			if(loggedIn)
				System.out.println("Enter the command : 1) Give a test 0) Log Out");
			else
				System.out.println("Enter the command : 1) Register 2) Login 0) Exit");
    	  String str = sc.nextLine();
    	  int choice = Integer.parseInt(str);
    	  if(loggedIn) {
	    	  switch(choice) {
		    	  case 1:
		    		  if(currentStudent==null) {
		    			  System.out.println("Please login first");
		    			  break;
		    		  }
		    		  Test.startTest(c, currentStudent, sc);
		    		  break;
		    	  case 0:
		    		currentStudent = null;
		    		break;
		    	  default:
		    		  System.out.println("Enter the correct choice");
	    	  }
    	  }
    	  else {
    		  switch(choice) {
	    	  case 1:
	    		  currentStudent = Student.Register(c, sc);
	    		  break;
	    	  case 2:
	    		  currentStudent = Student.logIn(c, sc);
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
	
	public static Student getStudentProfile(Connection c, String roll_no) {
		Student student = null;
		try {			
			while(true) {
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM student WHERE roll_no = '" + roll_no +"';" );
		        if ( rs.next() ) {
		            String  emailId= rs.getString("email_id");
		            String  batch= rs.getString("batch");
		            String  uname= rs.getString("suname");
		            PPPUser user = PPPUser.logIn(c, uname);
		            String  branch= rs.getString("branch");
					student = new Student();
					student.setRoll_no(roll_no);
					student.setEmailID(emailId);
					student.setSuname(user);
					student.setBatch(batch);
					student.setBranch(branch);
		            break;
		         }
		        else {
		        	System.out.println("Student doen't exist");
		        }
		         rs.close();
		         stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
		return student;
	}
	
}
