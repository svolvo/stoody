package Model;

public class EventParticipant {

	private int _userId;
	private String _firstName;
	private String _lastName;
	private eEventStatus _eventStatus;

	
	public EventParticipant(int _userId, String _firstName, String _lastName, eEventStatus _eventStatus) {
		super();
		this._userId = _userId;
		this._firstName = _firstName;
		this._lastName = _lastName;
		this._eventStatus = _eventStatus;
	}
	
	
	public String get_firstName() {
		return _firstName;
	}
	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}
	public String get_lastName() {
		return _lastName;
	}
	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}
	public eEventStatus get_eventStatus() {
		return _eventStatus;
	}
	public void set_eventStatus(eEventStatus _eventStatus) {
		this._eventStatus = _eventStatus;
	}
	public int get_userId() {
		return _userId;
	}
	public void set_userId(int _userId) {
		this._userId = _userId;
	}
	
	
}
