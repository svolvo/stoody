package Model;

import java.util.Date;

public class Course {

	private String _courseName;
	private Date _courseStartDate = new Date();
	private Date _courseEndDate = new Date();
	private Date _startTimeEveryWeek = new Date();
	private Date _endTimeEveryWeek = new Date();
	private String _location;
	private eCourseDays _courseDay;
	
	
	private Date moedA_Start = new Date();
	private Date moedA_End = new Date();
	private String _locationA;
	
	private Date moedB_Start = new Date();
	private Date moedB_End = new Date();
	private String _locationB;
	
	
	public Course() {}

	
	
	public Course(String _courseName, Date _courseStartDate, Date _courseEndDate, Date _startTimeEveryWeek,
			Date _endTimeEveryWeek, String _location, eCourseDays _courseDay, Date moedA_Start, Date moedA_End,
			String _locationA, Date moedB_Start, Date moedB_End, String _locationB) {
		super();
		this._courseName = _courseName;
		this._courseStartDate = _courseStartDate;
		this._courseEndDate = _courseEndDate;
		this._startTimeEveryWeek = _startTimeEveryWeek;
		this._endTimeEveryWeek = _endTimeEveryWeek;
		this._location = _location;
		this._courseDay = _courseDay;
		this.moedA_Start = moedA_Start;
		this.moedA_End = moedA_End;
		this._locationA = _locationA;
		this.moedB_Start = moedB_Start;
		this.moedB_End = moedB_End;
		this._locationB = _locationB;
	}



	public String get_courseName() {
		return _courseName;
	}
	public void set_courseName(String _courseName) {
		this._courseName = _courseName;
	}
	public Date get_courseStartDate() {
		return _courseStartDate;
	}
	public void set_courseStartDate(Date _courseStartDate) {
		this._courseStartDate = _courseStartDate;
	}
	public Date get_courseEndDate() {
		return _courseEndDate;
	}
	public void set_courseEndDate(Date _courseEndDate) {
		this._courseEndDate = _courseEndDate;
	}
	public Date get_startTimeEveryWeek() {
		return _startTimeEveryWeek;
	}
	@SuppressWarnings("deprecation")
	public void set_startTimeEveryWeek(Date _startTimeEveryWeek) {
		this._startTimeEveryWeek.setHours(_startTimeEveryWeek.getHours());
		this._startTimeEveryWeek.setMinutes(_startTimeEveryWeek.getMinutes());

	}
	public Date get_endTimeEveryWeek() {
		return _endTimeEveryWeek;
	}
	public void set_endTimeEveryWeek(Date _endTimeEveryWeek) {
		this._endTimeEveryWeek.setHours(_endTimeEveryWeek.getHours());
		this._endTimeEveryWeek.setMinutes(_endTimeEveryWeek.getMinutes());
	}
	public String get_location() {
		return _location;
	}
	public void set_location(String _location) {
		this._location = _location;
	}
	public eCourseDays get_courseDay(){
		return _courseDay;
	}
	public void set_courseDay(eCourseDays day) {
		this._courseDay = day;
	}
	
	
	
	
	//MOED A//
	public Date get_moedA_Start() {
		return moedA_Start;
	}
	public void set_moedA_Start(Date moedA_start, Date _startA) {
		this.moedA_Start = moedA_start;
		this.moedA_Start.setHours(_startA.getHours());
		this.moedA_Start.setMinutes(_startA.getMinutes());
	}
	public Date get_moedA_End() {
		return moedA_End;
	}
	public void set_moedA_End(Date moedA_end, Date _endA) {
		this.moedA_End = moedA_end;
		this.moedA_End.setHours(_endA.getHours());
		this.moedA_End.setMinutes(_endA.getMinutes());
	}
	public String get_locationA() {
		return _locationA;
	}
	public void set_locationA(String _locationA) {
		this._locationA = _locationA;
	}
	
	///MOED B///
	public Date get_moedB_Start() {
		return moedB_Start;
	}
	public void set_moedB_Start(Date moedB_start, Date _startB) {
		this.moedB_Start = moedB_start;
		this.moedB_Start.setHours(_startB.getHours());
		this.moedB_Start.setMinutes(_startB.getMinutes());
	}
	public Date get_moedB_End() {
		return moedB_End;
	}
	public void set_moedB_End(Date moedB_end, Date _endB) {
		this.moedB_End = moedB_end;
		this.moedB_End.setHours(_endB.getHours());
		this.moedB_End.setMinutes(_endB.getMinutes());
	}
	public String get_locationB() {
		return _locationB;
	}
	public void set_locationB(String _locationB) {
		this._locationB = _locationB;
	}
	
	
	
	
}
