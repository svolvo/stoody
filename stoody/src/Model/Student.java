package Model;

public class Student extends RegularUser {

	public Student(int id, String firstName, String lastName) 
	{
		super(id, firstName, lastName, Integer.toString(id), eUserType.student);
	}

	public Student(int id, String firstName, String lastName, String password) 
	{
		super(id, firstName, lastName, password, eUserType.student);
	}
}
