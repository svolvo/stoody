package Model;

import java.util.Date;

public class CourseDetails {

	
	
	private int _courseId;
	private Course _course;
	private eUserCourseStatus _status;
	

	public CourseDetails(int _courseId, Course _course, eUserCourseStatus _status) {
		super();
		this._courseId = _courseId;
		this._course = _course;
		this._status = _status;
	}
	
	
	public int get_courseId() {
		return _courseId;
	}


	public void set_courseId(int _courseId) {
		this._courseId = _courseId;
	}


	public Course get_course() {
		return _course;
	}


	public void set_course(Course _course) {
		this._course = _course;
	}


	public eUserCourseStatus get_status() {
		return _status;
	}


	public void set_status(eUserCourseStatus _status) {
		this._status = _status;
	}
	
}
