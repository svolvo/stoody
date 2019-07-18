package Model;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
	
	
	
	
	public static String GetDateTimeByStringFormat(Date date)
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		return dateFormat.format(date);  
	}
	
	public static String GetShortDateString(Date date)
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		return dateFormat.format(date);  
	}
	
	public static Date GetDateFromString(String dateStr)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startDate = new Date();
		try {
			   return format.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	private static boolean ExecuteNonQuery(String sql)
	{
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
	      }
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


	
	
	
	
	
	
	
	
	  public static ArrayList<RegularUser> SelectUsers(  ) {

		  ArrayList<RegularUser> users = new ArrayList<RegularUser>();
		  
		  
		   Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		      c.setAutoCommit(false);

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM user;" );
		      
		      while ( rs.next() ) {
		         int userId = rs.getInt("id");
		         int userType = rs.getInt("user_type");
		         String  firstName = rs.getString("first_name");
		         String lastName = rs.getString("last_name");
		         
		         if (userType == eUserType.student.ordinal())
		    	  {
		    		  users.add(new Student(userId, firstName, lastName, ""));
		    	  }
		    	  else if (userType == eUserType.teacher.ordinal())
		    	  {
		    		  users.add(new Teacher(userId, firstName, lastName, ""));
		    	  }
		         
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		   }
		   
		   return users;
		  }

	  


	  

	/**
	 * returns a RegularUser object by type if the user exists in the data base and has a matching password
	 * returns null otherwise.
	 */
	public static RegularUser Login(String username, String password) {
		RegularUser regularUser = null;
		String sql = String.format("SELECT * FROM user WHERE id = '%s' AND password = '%s';" , username, password);
		
		
		ResultSet resultSet = null;
  		Connection c = null;
		Statement stmt = null;
		try {
	         Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		      c.setAutoCommit(false);
		      stmt = c.createStatement();
		      resultSet = stmt.executeQuery(sql);
		      // go to first row
		      resultSet.next();
		      
		      // parse properties
			  int userType = resultSet.getInt("user_type");
	    	  int userId = resultSet.getInt("id");
			  String firstName = resultSet.getString("first_name");
	    	  String lastName = resultSet.getString("last_name");
	    	  
	    	  
	    	  if (userType == eUserType.student.ordinal())
	    	  {
	    		  regularUser = new Student(userId, firstName, lastName, "");
	    	  }
	    	  else if (userType == eUserType.teacher.ordinal())
	    	  {
	    		  regularUser = new Teacher(userId, firstName, lastName, "");
	    	  }
		      
		      resultSet.close();
		      stmt.close();
		      c.close();
		      
		   } catch ( Exception e ) {}   
		
		
		return regularUser;
	}


	public static boolean AddUser(RegularUser user) {
		
		 String sql = String.format("INSERT INTO user (id,first_name,last_name,password, user_type) "
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
		 RegularUser regularUser = null;
		 String sql = String.format("SELECT * FROM user WHERE ID = %d;" ,userId);
		
		 
		 Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		      c.setAutoCommit(false);

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      //FIRST_NAME,LAST_NAME,PASSWORD, USER_TYPE
		      while ( rs.next() ) {
		    	  int userType = rs.getInt("user_type");
		    	  String firstName = rs.getString("first_name");
		    	  String lastName = rs.getString("last_name");

		    	  if (userType == eUserType.student.ordinal())
		    	  {
		    		  regularUser = new Student(userId, firstName, lastName, "");
		    	  }
		    	  else if (userType == eUserType.teacher.ordinal())
		    	  {
		    		  regularUser = new Teacher(userId, firstName, lastName, "");
		    	  }
			      
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		   }
		   
		   
		return regularUser;
	}


	public static boolean AddEvent(StoodyEvent event, int userId) {

		// insert new event to event table
		 String sql = String.format("INSERT into event (event_type, title, start_date_time, end_date_time, location) VALUES (%d, '%s', '%s', '%s', '%s');", 
				 event.getEventType().ordinal(), event.getTitle(),  GetDateTimeByStringFormat(event.getStartDateTime()) , GetDateTimeByStringFormat(event.getEndDateTime()), event.getLocation());

		 
		 boolean result = ExecuteNonQuery(sql);
		 if (!result)
			 return false;
		 
		 Connection c = null;
		 Statement stmt = null;

		
		 
		 String getEventId = "SELECT max(event_id) from event;;";
		 int eventId = -1;
		 try {
			 Class.forName("org.sqlite.JDBC");
			 c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
			 c.setAutoCommit(false);
			 stmt = c.createStatement();
			 ResultSet resultSet = stmt.executeQuery(getEventId);
			 resultSet.next();
			 eventId = resultSet.getInt("max(event_id)");
			 stmt.close();
			 c.commit();
			 c.close();
		 } catch ( Exception e ) {
			 return false;
		 }
		 
		 
		 String insertUserEvent = String.format("INSERT into user_events (user_id, event_id, status) VALUES (%d, %d, %d);", 
				 userId, eventId, eEventStatus.attending.ordinal());
		 
		 
		 
		 return ExecuteNonQuery(insertUserEvent);
		 
	}


	public static ArrayList<StoodyEvent> GetEventsListByDate(int userId, Date date) {
		// create query limits
		Date dayAfter = new Date(date.getTime() + TimeUnit.DAYS.toMillis( 1 ));
		
		// TODO Auto-generated method stub
		String sql = String.format(
				"SELECT * FROM event  e\n" + 
				"JOIN user_events ue USING(event_id) \n" + 
				"WHERE e.start_date_time >= '%s' \n" + 
				"AND e.end_date_time < '%s' \n" + 
				"AND ue.user_id = %d \n" + 
				"AND ue.status != 2 \n" + // don't retrieve cancelled events
				"ORDER BY start_date_time ASC;",
				GetShortDateString(date), GetShortDateString(dayAfter), userId); 
		
		 ArrayList<StoodyEvent> events = new ArrayList<StoodyEvent>();
		  
		  
		   Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		      c.setAutoCommit(false);

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      while ( rs.next() ) {
		    	  // id
		    	  int eventId = rs.getInt("event_id");
		    	  // type
		    	  eEventType eventType = eEventType.values()[rs.getInt("event_type")];
		    	  // title
		    	  String title = rs.getString("title");
		    	  // start date time
		    	  Date startDateTime = GetDateFromString(rs.getString("start_date_time"));
		    	  // end date time
		    	  Date endDateTime = GetDateFromString(rs.getString("end_date_time"));
		    	  // location
		    	  String location = rs.getString("location");
		    
		    	  // add event to collection
		    	  events.add(new StoodyEvent(eventId, eventType, title, startDateTime, endDateTime, location));
		    
		   
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		   }
		   
		   return events;
	}	
	
	
	public static ArrayList<EventParticipant> GetEventParticipants(int eventId) {

		String sql = String.format(
				"SELECT u.id, u.first_name, u.last_name, ue.status FROM \n" + 
				"user u \n" + 
				"JOIN user_events ue ON (u.id = ue.user_id)\n" + 
				"WHERE ue.event_id = %d;", eventId);
		
		 ArrayList<EventParticipant> participiants = new ArrayList<EventParticipant>();
		  
		  
		   Connection c = null;
		   Statement stmt = null;
		   try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		      c.setAutoCommit(false);

		      stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      while ( rs.next() ) {
		    	  // user id
		    	  int userId = rs.getInt("id");
		    	  // Event Status
		    	  eEventStatus eventStatus = eEventStatus.values()[rs.getInt("status")];
		    	  // firstName
		    	  String firstName = rs.getString("first_name");
		    	  // title
		    	  String lastName = rs.getString("last_name");
		    
		    	  // add event to collection
		    	  participiants.add(new EventParticipant(userId,firstName, lastName, eventStatus));
		    
		   
		      }
		      rs.close();
		      stmt.close();
		      c.close();
		   } catch ( Exception e ) {
		   }
		   
		   return participiants;
	}	
	
	
}

