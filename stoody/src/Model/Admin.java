package Model;

public class Admin extends User{

	public static final String USER_NAME = "Admin";
	public static final String PASSWORD = "HITadmin1234";
	
	public Admin() {
		super(eUserType.administrator, USER_NAME, PASSWORD);
	}

}
