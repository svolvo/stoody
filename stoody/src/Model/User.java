package Model;

public abstract class User {

		// Members
		private eUserType _userType;
		private String _name;
		private String _password;

		
		// Constructor
		public User(eUserType userType, String firstName, String password)
		{
			set_userType(userType);
			set_firstName(firstName);
			set_password(password);
		}

	
		public eUserType get_userType() {
			return _userType;
		}

		protected void set_userType(eUserType _userType) {
			this._userType = _userType;
		}

		
		public String get_firstName() {
			return _name;
		}

		protected void set_firstName(String _firstName) {
			this._name = _firstName;
		}
		
		public String get_password() {
			return _password;
		}

		public void set_password(String _password) {
			this._password = _password;
		}

		
}
