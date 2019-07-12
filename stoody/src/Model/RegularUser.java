package Model;

public abstract class RegularUser {
	

	// Members
	private int _id;
	private String _firstName;
	private String _lastName;
	private String _password;
	private eUserType _userType;
	
	// Constructor
	public RegularUser(int id, String firstName, String lastName, String password, eUserType userType)
	{
		set_id(id);
		set_firstName(firstName);
		set_lastName(lastName);
		set_password(password);
		set_userType(userType);
	}

	public int get_id() {
		return _id;
	}

	protected void set_id(int _id) {
		this._id = _id;
	}

	public String get_lastName() {
		return _lastName;
	}

	protected void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}

	public String get_firstName() {
		return _firstName;
	}

	protected void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}

	public eUserType get_userType() {
		return _userType;
	}

	protected void set_userType(eUserType _userType) {
		this._userType = _userType;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}
	
	
	
	// Methods
	/**
	 * Returns true if the user should reset the password false otherwise
	 */
	public boolean ShouldResetPassword() {
		try {
			return _id == Integer.parseInt(_password);
		}
		catch (NumberFormatException e){
			return false;
		}
	}
	
	
	
	
	
	
	
	
}
