package Model;

public class DataLayer {

	
	/**
	 * returns the user type by the credentials, if login fails returns unknown user.
	 */
	public static eUserType Login(String username, String password)
	{
		if (username.equals("Admin") && password.equals("HITadmin1234"))
			return eUserType.administrator;
		
		
		RegularUser user = SQLiteDataLayer.Login(username, password);
		if (user != null)
			return user.get_userType();
		
		
		return eUserType._unknown;
	}
	
	
	/**
	 * Invoked by an administrator to add new users to the database
	 * @param user regular user object
	 * @return true if user was successfully added false otherwise.
	 */
	public static boolean AddNewRegularUser(RegularUser user) {

		return SQLiteDataLayer.AddUser(user);
	}


	public static RegularUser GetUserById(int userId) {

		return SQLiteDataLayer.GetUserById(userId);
	}
}
