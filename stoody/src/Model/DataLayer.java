package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataLayer {

	// Singleton member
	public static DataLayer _instance;
	
	// Singleton Property - holds current user information
	public static DataLayer get_Instance() {
		if (_instance == null)
			_instance = new DataLayer();
		return _instance;
	}
	
	private User _currentUser;
	
	/**
	 * Gets the currently logged in user of the system. 
	 */
	public User get_currnetUser() {
		return _currentUser;
	}

	/**
	 * Helper method which casts the current user to a RegularUser object 
	 */
	public RegularUser GetCurrentRegularUser() {
		eUserType userType = this.get_currnetUser().get_userType();
		if (userType == eUserType.teacher || userType == eUserType.student)
			return (RegularUser) this.get_currnetUser();
		return null;
	}
	
	
	
	/**
	 * returns the user type by the credentials, if login fails returns unknown user.
	 */
	public  eUserType Login(String username, String password)
	{
		if (username.equals("Admin") && password.equals("HITadmin1234"))
		{
		 	get_Instance()._currentUser = new Admin();
			return eUserType.administrator;
		}
		
		RegularUser user = SQLiteDataLayer.Login(username, password);
		if (user != null)
		{
			get_Instance()._currentUser  = user;
			return user.get_userType();
		}
		
		return eUserType._unknown;
	
	}
	
	/**
	 * Invoked by an administrator to add new users to the database
	 * @param user regular user object
	 * @return true if user was successfully added false otherwise.
	 */
	public  boolean AddNewRegularUser(RegularUser user) {
		// Authorization test
		if (get_Instance().get_currnetUser().get_userType() == eUserType.administrator)
			return SQLiteDataLayer.AddUser(user);
		return false;
	}


	/**
	 * Returns a user (Student or Teacher) by Id.
	 * @param userId (user id is also the user name of the user
	 */
	public static RegularUser GetUserById(int userId) {

		// TODO
		return SQLiteDataLayer.GetUserById(userId);
	}
	
	/**
	 * A teacher can add a new course to the system.
	 * @param course
	 * @return
	 */
	public boolean AddCourse(Course course)
	{
		// TODO
		return false;
	}
	
	/**
	 * Adds a new event to the system, the owner of the event is the current logged in user.
	 * @param event
	 * @return true if the event was successfully added, false otherwise.
	 */
	public static boolean AddEvent(StoodyEvent event) {

		// Authorization - make suer that only the teacher adds a new course.
		RegularUser user = get_Instance().GetCurrentRegularUser();
		if (user != null)
		{			
			// user is either a student or a teacher
			if (user.get_userType() == eUserType.teacher ||
				(user.get_userType() == eUserType.student && event.getEventType() == eEventType.user_meeting))
			{
				return SQLiteDataLayer.AddEvent(event, user.get_id());
			}
		}
		return false;
	}
	
	/**
	 * Adds a new event of type meeting and attaches the event to all of the users
	 * @param event StoodyEvent of type meeting
	 * @param userIds list of RegularUser id's
	 * @return true if the event was successfully added, false otherwise
	 */
	public boolean AddMeeting(StoodyEvent event, List<Integer> userIds)
	{
		// TODO
		return false;
	}
	
	
	/**
	 * Get's a chronologically sorted list of Stoody events which the currently logged in user contains on a given day.
	 * @param date
	 * @return
	 */
	public  ArrayList<StoodyEvent> GetEventsListByDate(Date date)
	{
		RegularUser user = get_Instance().GetCurrentRegularUser();
	
		return SQLiteDataLayer.GetEventsListByDate(user.get_id(), date);
	}
	
	/**
	 * Invoked if the currently logged in user wishes to RSVP to a meeting event.
	 * @param event
	 * @param willAttend true if the user wishes to attend the event, false otherwise
	 * @return true if the event was RSVP'd successfully.
	 */
	public  boolean SetEventStatus(StoodyEvent event, boolean willAttend)
	{
		RegularUser user;
		if (get_Instance().get_currnetUser().get_userType() == eUserType.student)
		{
			user =  (RegularUser) get_Instance().get_currnetUser();
			//TODO
		}
		return true;
	}
	
	/**
	 * Get the list of all the Regular Users of the System.
	 * Note that the password field will be empty in this call.
	 */
	public ArrayList<RegularUser> GetReuglarUsersList() {
		return SQLiteDataLayer.SelectUsers();
	}


	/**
	 * Returns a list of all the invites of a meetings and their RSVP state
	 * @param event
	 * @return
	 */
	public ArrayList<EventParticipant> GetEventParticipants(StoodyEvent event) {
		
		return SQLiteDataLayer.GetEventParticipants(event.getId());

	}
	
	/**
	 * Returns a list of all the courses in the system and the logged in user status of subscription
	 */
	public ArrayList<CourseDetails> getCourseList()
	{
		// TODO
		return null;
	}
	
	/**
	 * Adds the logged in user to the course.
	 */
	public boolean SignUpToCourse(CourseDetails courseDetails)
	{
		// TODO
		return false;
	}

	/**
	 * Attempts to change the password for the currently logged in user (only regular user) 
	 * @param password
	 * @return true if password was changed successfully, false otherwise.
	 */
	public boolean ChagngePassword(String _password) {
		// Changes a password to a regular user
		
		return false;
	}
}
