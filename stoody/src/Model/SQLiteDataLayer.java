package Model;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	
	
	final static String CREATE_COURSE_TABLE = 
			"CREATE TABLE \"course\" (\n" + 
			"	\"course_id\"	INTEGER PRIMARY KEY AUTOINCREMENT,\n" + 
			"	\"name\"	INTEGER NOT NULL,\n" + 
			"	\"location\"	TEXT NOT NULL,\n" + 
			"	\"day_of_week\"	INTEGER NOT NULL,\n" + 
			"	\"start_time\"	TEXT NOT NULL,\n" + 
			"	\"end_time\"	TEXT NOT NULL,\n" + 
			"	\"course_start_date\"	TEXT NOT NULL,\n" + 
			"	\"course_end_date\"	TEXT NOT NULL," +
			"	\"moed_a_start_date_time\"	TEXT NOT NULL,\n" + 
			"	\"moed_a_end_date_time\"	TEXT NOT NULL,\n" + 
			"	\"moed_a_location\"	TEXT NOT NULL,\n" + 
			"	\"moed_b_start_date_time\"	TEXT NOT NULL,\n" + 
			"	\"moed_b_end_date_time\"	TEXT NOT NULL,\n" + 
			"	\"moed_b_location\"	TEXT NOT NULL\n" + 
			");";
	
	
	final static String CREATE_COURSE_EVENTS_TABLE = 
			"CREATE TABLE \"course_events\" (\n" + 
			"	\"course_id\"	INTEGER NOT NULL,\n" + 
			"	\"event_id\"	INTEGER NOT NULL\n" + 
			");";
	
	final static String CREATE_STUDENT_COURSES_TABLE = 
			"CREATE TABLE \"student_courses\" (\n" + 
			"	\"user_id\"	INTEGER NOT NULL,\n" + 
			"	\"course_id\"	INTEGER NOT NULL\n" + 
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
	
	/**
	 * Executes an SQL statement where the return value doesn't matter
	 * @param sql
	 * @return true if execution succeeded, false otherwise.
	 */
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
	 * Executes an SQL statement and then parses an integer for the specified fieldName
	 * @param sql
	 * @param fieldName
	 * @param error returns error if execution or cast fails
	 * @return field value from SQL
	 */
	private static int ExecuteScalar(String sql, String fieldName, int error)
	{
		 int result = error;
		 Connection c = null;
		 Statement stmt = null;
		 try {
			 Class.forName("org.sqlite.JDBC");
			 c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
			 c.setAutoCommit(false);
			 stmt = c.createStatement();
			 ResultSet resultSet = stmt.executeQuery(sql);
			 resultSet.next();
			 result = resultSet.getInt(fieldName);
			 stmt.close();
			 c.commit();
			 c.close();
			 return result;
		 } catch ( Exception e ) {
			 return error;
		 }
		 
	}
	
	/**
	 * Returns the max of a field in a particular table
	 */
	private static int GetMaxFieldInTable(String fieldName, String tableName)
	{
		String sqlGetMax = String.format("SELECT max(%s) as %s from %s;", fieldName, fieldName, tableName);
		return ExecuteScalar(sqlGetMax, fieldName, 0);
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
			createTable(CREATE_COURSE_TABLE);
			createTable(CREATE_USER_EVENTS_TABLE);	
			createTable(CREATE_COURSE_EVENTS_TABLE);
			createTable(CREATE_STUDENT_COURSES_TABLE);
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
		 
		 
		
		 
		 int eventId = GetMaxFieldInTable("event_id", "event");

		 
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

	public static boolean AddCourse(Course course, int userId) {
		// TODO Auto-generated method stub
		/*
		 INSERT INTO course (name, location, day_of_week, start_time, end_time, course_start_date, course_end_date, 
moed_a_start_date_time, moed_a_end_date_time, moed_a_location, moed_b_start_date_time, moed_b_end_date_time, moed_b_location)
VALUES ('math', 1, '8-200', '19:00', '21:00', '2019-01-01', '2019-06-01',  
'2019-07-01 09:00', '2019-07-01 12:00', '8-210', 
'2019-07-17 09:00', '2019-07-17 12:00', '8-300' );
		 * */
		
		String sql = String.format(
				"INSERT INTO course (name, location, day_of_week, start_time, end_time, course_start_date, course_end_date, \n" + 
				"moed_a_start_date_time, moed_a_end_date_time, moed_a_location, moed_b_start_date_time, moed_b_end_date_time, moed_b_location)\n" + 
				"VALUES ('%s', '%s', %d, '%s', '%s', '%s', '%s',  \n" + 
				"'%s', '%s', '%s', \n" + 
				"'%s', '%s', '%s' );", 
				course.get_courseName(), 
				course.get_location(),
				course.get_courseDay().ordinal(), 
				DateHelper.DateToStringGetDisplayTime(course.get_startTimeEveryWeek()),
				DateHelper.DateToStringGetDisplayTime(course.get_endTimeEveryWeek()),
				DateHelper.GetShortDateString(course.get_courseStartDate()),
				DateHelper.GetShortDateString(course.get_courseEndDate()),
				DateHelper.GetDateTimeByStringFormat(course.get_moedA_Start()),
				DateHelper.GetDateTimeByStringFormat(course.get_moedA_End()),
				course.get_locationA(),
				DateHelper.GetDateTimeByStringFormat(course.get_moedB_Start()),
				DateHelper.GetDateTimeByStringFormat(course.get_moedB_End()),
				course.get_locationB()
				);
		
		
		if (!ExecuteNonQuery(sql))
			 return false;
		
		// get max event id 
		int eventId = GetMaxFieldInTable("event_id", "event");
		// get max courseId
		int courseId = GetMaxFieldInTable("course_id", "course");
		 
		// current start date
		Date current = course.get_courseStartDate();
		
		// set start time to match current date
		current.setHours(course.get_startTimeEveryWeek().getHours());
		current.setMinutes(course.get_startTimeEveryWeek().getMinutes());
		int hours = course.get_endTimeEveryWeek().getHours() - course.get_startTimeEveryWeek().getHours();
		int minutes = course.get_endTimeEveryWeek().getMinutes() - course.get_startTimeEveryWeek().getMinutes();

		
		// add days to fit the day of the week after the start date
		int dayOfWeekStart = current.getDay();
		int addDays = 0;
		while (course.get_courseDay().ordinal() != dayOfWeekStart)
		{
			// increment add days and terminate condition
			dayOfWeekStart = (dayOfWeekStart + 1) % 7;
			addDays++;
		}

		if (addDays != 0)
			current = DateHelper.AddDays(current, addDays);
		

		// set end date upper limit for course events
		Date end = DateHelper.AddDays(course.get_courseEndDate(), 1);
		
		
		int count = 1;
		while (current.before(end))
		{
			// Add course event to events table 
			StoodyEvent stoodyEvent = new StoodyEvent(eEventType.course, course.get_courseName(), current, DateHelper.AddTime(current, hours, minutes), course.get_location());			
			if (!AddEvent(stoodyEvent, userId))
				return false;
			
			// Add records to course_events table
			String sqlUpdateCourseEvents = String.format("INSERT INTO course_events (course_id, event_id) VALUES (%d, %d);", courseId, eventId + count);
			if (!ExecuteNonQuery(sqlUpdateCourseEvents))
				return false;
			
			// increment current by a week
			current = DateHelper.AddDays(current, 7);
			count++;
		}
		
		
		
		
		
		return true;
		
	}

	public static ArrayList<CourseDetails> GetCourseList(int get_id) {
		
		ArrayList<CourseDetails> courses = new ArrayList<CourseDetails>();
		// get all the course id which the users is already subscribed to.
		String sqlGetUserCourseIds = "SELECT course_id FROM student_courses;";

		ArrayList<Integer> userCourses = new ArrayList<Integer>();
		Connection c = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		    c.setAutoCommit(false);

		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sqlGetUserCourseIds);
		      
		    while ( rs.next() ) {
		    	// add course id
		    	userCourses.add(rs.getInt("course_id"));
		    }
		    rs.close();
		    stmt.close();
		    c.close();
		} catch ( Exception e ) {		
		}
		

		// Get all course list
		String sqlGetCourseList = "SELECT * FROM course;";
		
		c = null;
		stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:stoody.db");
		    c.setAutoCommit(false);

		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sqlGetCourseList);
		      		    
		    while ( rs.next() ) {
		    	
		    	int course_id = rs.getInt("course_id");
		    	
		    	// create course object
		    	String courseName = rs.getString("name");
		    	Date courseStartDate = DateHelper.GetDateFromString(rs.getString("course_start_date")); 
		    	Date courseEndDate = DateHelper.GetDateFromString(rs.getString("course_end_date")); 
		    	Date startTimeEveryWeek = DateHelper.GetDateFromString("2001-01-01 " + rs.getString("start_time")); 
				Date endTimeEveryWeek = DateHelper.GetDateFromString("2001-01-01 " + rs.getString("end_time")); 
				String location = rs.getString("location");
				eCourseDays courseDay = eCourseDays.values()[rs.getInt("day_of_week")];
				Date moedA_Start  = DateHelper.GetDateFromString(rs.getString("moed_a_start_date_time")); 
				Date moedA_End = DateHelper.GetDateFromString(rs.getString("moed_a_end_date_time")); 
				String locationA = rs.getString("moed_a_location");
				Date moedB_Start = DateHelper.GetDateFromString(rs.getString("moed_b_start_date_time"));  
				Date moedB_End = DateHelper.GetDateFromString(rs.getString("moed_b_end_date_time"));  
				String locationB = rs.getString("moed_b_location");
		    	 
		    	
		    	
		    	Course course = new Course(courseName, courseStartDate, courseEndDate, startTimeEveryWeek,
		    			endTimeEveryWeek, location, courseDay, moedA_Start, moedA_End,
		    			locationA, moedB_Start, moedB_End, locationB);
		    	
		    	
		    	eUserCourseStatus subscriptionStatus = eUserCourseStatus.unsubscribed;
		    	
		    	if (userCourses.contains(course_id))
		    		subscriptionStatus = eUserCourseStatus.subscribed;
		    	
		    	
		    	courses.add(new CourseDetails(course_id, course, subscriptionStatus));
		    }
		    rs.close();
		    stmt.close();
		    c.close();
		} catch ( Exception e ) {		
		}

		
		
		return courses;
	}	
	

	
}

