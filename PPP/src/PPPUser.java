import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class PPPUser {
	private String uname;
	private String passwd;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public static PPPUser Register(Connection c, Scanner s) {
		PPPUser user;
		try {
			user = new PPPUser();
			System.out.println("enter your username");
			String str=s.nextLine();
			while(checkRegistered(c,str)){
				System.out.println("entered username is already exists, choose another one");
				str=s.nextLine();
			}
			user.setUname(str);
			System.out.println("enter password");
			str=s.nextLine();
			user.setPasswd(str);

			Statement stmt = c.createStatement();
	        String sql = "INSERT INTO pppuser (uname,passwd) "
	           + "VALUES ("+ user.uname + "', '" + user.passwd + "');";
	        stmt.executeUpdate(sql);
	
	        stmt.close();
	        c.commit();
	        System.out.println("User registered successfully");
		} catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         user = null;
	      }
		return user;
	}
	
	public static boolean checkRegistered(Connection c, String uname) {
		boolean result = false;
		try {
			Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM pppuser WHERE uname = '" +  uname + "';");
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
	
	public static PPPUser logIn(Connection c, String uname) {
		PPPUser user = null;
		try {			
			while(true) {
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM pppuser WHERE uname = '" + uname +"';" );
		        if ( rs.next() ) {
		            String  password = rs.getString("passwd");
					user = new PPPUser();
					user.setPasswd(password);
					user.setUname(uname);
		            break;
		         }
		        else {
		        	System.out.println("User doen't exist");
		        }
		         rs.close();
		         stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
		return user;
	}
}