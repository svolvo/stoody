package Model;

import java.util.Date;

public class CourseDetails {

	
	
	private int _courseId;
	private String _courseName;
	private eCourseDays _dayOfTheWeek;
	private Date _moedA;
	private Date _moedB;
	private eUserCourseStatus _status;
	
	
	public CourseDetails(int _courseId, String _courseName, eCourseDays _dayOfTheWeek, Date _moedA, Date _moedB,
			eUserCourseStatus _status) {
		this._courseId = _courseId;
		this._courseName = _courseName;
		this._dayOfTheWeek = _dayOfTheWeek;
		this._moedA = _moedA;
		this._moedB = _moedB;
		this._status = _status;
	}


	public int get_courseId() {
		return _courseId;
	}


	public void set_courseId(int _courseId) {
		this._courseId = _courseId;
	}


	public String get_courseName() {
		return _courseName;
	}


	public void set_courseName(String _courseName) {
		this._courseName = _courseName;
	}


	public eCourseDays get_dayOfTheWeek() {
		return _dayOfTheWeek;
	}


	public void set_dayOfTheWeek(eCourseDays _dayOfTheWeek) {
		this._dayOfTheWeek = _dayOfTheWeek;
	}


	public Date get_moedA() {
		return _moedA;
	}


	public void set_moedA(Date _moedA) {
		this._moedA = _moedA;
	}


	public Date get_moedB() {
		return _moedB;
	}


	public void set_moedB(Date _moedB) {
		this._moedB = _moedB;
	}


	public eUserCourseStatus get_status() {
		return _status;
	}


	public void set_status(eUserCourseStatus _status) {
		this._status = _status;
	}
	
	
}
