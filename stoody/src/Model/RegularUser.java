package Model;

public abstract class RegularUser extends User{

	// Members
	private int _id;
	private String _lastName;
	private String _password;
	
	// Constructor
	public RegularUser(int id, String firstName, String lastName, String password, eUserType userType)
	{
		super(userType, firstName, password);
		set_id(id);
		set_lastName(lastName);
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
