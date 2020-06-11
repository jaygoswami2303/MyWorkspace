import java.sql.*;
import java.util.*;
import java.util.Date;

public class Test {
	private String test_id;
	private String sroll_no;
	private int score;
	private double duration;
	
	public String getTest_id() {
		return test_id;
	}

	public void setTest_id(String test_id) {
		this.test_id = test_id;
	}

	public String getSroll_no() {
		return sroll_no;
	}

	public void setSroll_no(String sroll_no) {
		this.sroll_no = sroll_no;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public Test(Connection c) {
		try {
			Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT max(test_id) FROM test");
	         if(rs.next()) {
	        	 if(rs.getString("max")==null)
	        		 test_id = "1";
	        	 else
	        		 test_id = "" + (Integer.parseInt(rs.getString("max"))+1);
	         }
	         rs.close();
	         stmt.close();
		} catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	      }
	}
	
	private static ArrayList<Question> getQuestions(Connection c,int count, String branch) {
		ArrayList<Question> questions = new ArrayList<Question>();
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM questions WHERE branch = '" + branch + "';");
			int i = 0;
			while(rs.next()) {
				if(i==count)
					break;
				Question question = new Question();
				question.setAnswer(rs.getString("answer"));
				question.setBranch(rs.getString("branch"));
				question.setQuestion_id(rs.getString("question_id"));
				question.setDescription(rs.getString("description"));
				question.setQtype(rs.getString("qtype"));
				question.setOp1(rs.getString("op1"));
				question.setOp2(rs.getString("op2"));
				question.setOp3(rs.getString("op3"));
				question.setOp4(rs.getString("op4"));
				i++;
				questions.add(question);
			}
		} catch(Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
		return questions;
	}
	
	public static void startTest(Connection c, Student currentStudent, Scanner sc) {
		Test t = new Test(c);
		t.setSroll_no(currentStudent.getRoll_no());
		try {
			Statement stmt = c.createStatement();
	        String sql = "INSERT INTO test (test_id,sroll_no,score,duration) "
	           + "VALUES ("+ t.getTest_id() + ", '" + t.getSroll_no() + "', '" + 0 + "', '" + 0 + "');";
	        stmt.executeUpdate(sql);
	
	        stmt.close();
	        c.commit();
		} catch(Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
		ArrayList<Question> questions = getQuestions(c, 2, currentStudent.getBranch());
		Date start = new Date();
		int score = 0;
		for(int i=0;i<questions.size();i++) {
			Question question = questions.get(i);
			System.out.print("Question " + (i+1) + ": ");
			System.out.println(question.getDescription());
			System.out.print("1) " + question.getOp1() + "  ");
			System.out.print("2) " + question.getOp2() + "  ");
			System.out.print("3) " + question.getOp3() + "  ");
			System.out.println("4) " + question.getOp4() + "  ");
			System.out.println("Enter your answer");
			String str = sc.nextLine();
			int choice = Integer.parseInt(str);
			if(choice==Integer.parseInt(question.getAnswer()))
				score++;
			try {
				Statement stmt = c.createStatement();
		        String sql = "INSERT INTO test_quest (ttest_id,tquestion_id,selected) "
		           + "VALUES ("+ t.getTest_id() + ", '" + question.getQuestion_id() + "', '" + choice + "');";
		        stmt.executeUpdate(sql);
		
		        stmt.close();
		        c.commit();
			} catch(Exception e) {
				System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			}
		}
		System.out.println("Thank you for giving a test. Your score is " + score);
		t.setScore(score);
		Date end = new Date();
		long diff = end.getTime() - start.getTime();
		t.setDuration(diff);
		try {
			Statement stmt = c.createStatement();
	        String sql = "UPDATE test SET score = '"
	          + t.getScore() + "', duration = '" + t.getDuration() + "' WHERE test_id = '" + t.getTest_id() + "';";
	        stmt.executeUpdate(sql);
	
	        stmt.close();
	        c.commit();
		} catch(Exception e) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		}
	}
}
