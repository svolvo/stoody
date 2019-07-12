package Model;

public class Teacher extends RegularUser {

	// Constructor
	public Teacher(int id, String firstName, String lastName) 
	{
		super(id, firstName, lastName, Integer.toString(id), eUserType.teacher);
	}

	public Teacher(int id, String firstName, String lastName, String password) 
	{
		super(id, firstName, lastName, password, eUserType.teacher);
	}
}
