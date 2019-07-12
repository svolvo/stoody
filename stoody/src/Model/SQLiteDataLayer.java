package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import stoody.User;

public class SQLiteDataLayer {
	
	
	final static String CREATE_USER_TABLE = 
			"CREATE TABLE user " +
                    "(id INT PRIMARY KEY     NOT NULL," +
                    " first_name             TEXT    NOT NULL, " + 
                    " last_name              TEXT    NOT NULL, " + 
                    " password               TEXT    NOT NULL, " + 
                    " user_type              INT     NOT NULL)"; 
	
	final static String CREATE_EVENT_TABLE = 
			"CREATE TABLE \"event\" (\n" + 
					"  \"event_id\"	INTEGER PRIMARY KEY AUTOINCREMENT,\n" + 
					"	\"event_type\"	INTEGER NOT NULL,\n" + 
					"	\"title\"	TEXT NOT NULL,\n" + 
					"	\"start_date_time\"	TEXT NOT NULL,\n" + 
					"	\"end_date_time\"	TEXT NOT NULL,\n" + 
					"	\"location\"	TEXT NOT NULL\n" + 
					");";
	
	final static String CREATE_USER_EVENTS_TABLE = 
			"CREATE TABLE \"user_events\" (\n" + 
					"	\"user_id\"	INTEGER NOT NULL,\n" + 
					"	\"event_id\"	INTEGER NOT NULL,\n" + 
					"	\"status\"	INTEGER NOT NULL\n" + 
					");";
	
	
	
	
	/**
	 * Executes a "Create table statement"
	 * @param sqlStatmentCreateTable
	 */
	private static void createTable(String sqlStatmentCreateTable) {
	      Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:stoody.db");

	         stmt = c.createStatement();
	         stmt.executeUpdate(sqlStatmentCreateTable);
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Table created successfully");
	   }
	
	
  	/**
  	 * Checks if the User table exists (indicating whether the database was previously created.
  	 * @return true if the User table exists, false otherwise.
  	 */
  	public static  boolean CheckIfTablesExist() {

  		String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='user';";
  		Connection c = null;
		Statement stmt = null;
		try {
	         Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      // should throw exception if no rows come back
		      rs.next();
		      int rowNumber = rs.getRow();
		      
		      rs.close();
		      stmt.close();
		      c.close();
		      
		      // if any rows come back this means that the USER table exists.
		      return rowNumber != 0;
		   } catch ( Exception e ) {
			   return false;
		   }   
  	}
	
  	
  	
  	/**
	 * Creates the SQL tables required to maintain data between sessions, if they don't already exist.
	 */
	public static void createTablesIfRequired() {
		// check if at least one table exist
		if (!SQLiteDataLayer.CheckIfTablesExist())
		{
			// SQLite DB file doesn't exist or does not contain any table defections.
			createTable(CREATE_USER_TABLE);
			createTable(CREATE_EVENT_TABLE);
			createTable(CREATE_USER_EVENTS_TABLE);	
		}	
	}


	
	
	
	
	public static void Insert( String username, int id, int role ) {
	      Connection c = null;
	      Statement stmt = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:stoody.db");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO USER (ID,NAME,ROLE) " +
	                        "VALUES (" + id + ", '" + username + "', " + role +");"; 
	         stmt.executeUpdate(sql);

	         stmt.close();
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
		}
	
	
	
	  public static List<RegularUser> SelectUsers(  ) {

		  List<RegularUser> users = new ArrayList<RegularUser>();
		  /*
		  
		   Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM USER;" );
		      
		      while ( rs.next() ) {
		         int id = rs.getInt("id");
		         String  name = rs.getString("name");
		          user = new User(name, id, 123);
		         users.add(user);
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation done successfully");
		   
		   */
		   return users;
		  }

	  


	  

	/**
	 * returns a RegularUser object by type if the user exists in the data base and has a matching password
	 * returns null otherwise.
	 */
	public static RegularUser Login(String username, String password) {
		// TODO Auto-generated method stub
		
		String sql = String.format("SELECT * FROM USER WHERE USER_NAME = '%s' AND PASSWORD = '%s';" , username, password);
		
		return null;
	}


	public static boolean AddUser(RegularUser user) {
		
		 String sql = String.format("INSERT INTO USER (id,first_name,last_name,password, user_type) "
		 						  + "VALUES (%d, '%s', '%s', '%s', %d);"
				 					,user.get_id(), user.get_firstName(), user.get_lastName(), user.get_password(), user.get_userType().ordinal());
		
		 
		 Connection c = null;
		 Statement stmt = null;
	      
	     try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
	         c.setAutoCommit(false);

	         stmt = c.createStatement();
	         
	         stmt.executeUpdate(sql);

	         stmt.close();
	         c.commit();
	         c.close();
	      } catch ( Exception e ) {
	        return false;
	      }
	     return true;
	}

	public static RegularUser GetUserById(int userId) {

		 String sql = String.format("SELECT * FROM USER WHERE ID = %d;" ,userId);
		
		 
		 Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      //FIRST_NAME,LAST_NAME,PASSWORD, USER_TYPE
		      while ( rs.next() ) {
		    	  int userType = rs.getInt("USER_TYPE");
		    	  String firstName = rs.getString("FIRST_NAME");
		    	  String lastName = rs.getString("LAST_NAME");
		    	  String password = rs.getString("PASSWORD");

		    	  
		    	  if (userType == eUserType.student.ordinal())
		    	  {
		    		  rs.close();
				      stmt.close();
				      c.close();
		    		  return new Student(userId, firstName, lastName, password);
		    	  }
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		   System.out.println("Operation done successfully");
		   
		   
		
		return null;
	}




}
