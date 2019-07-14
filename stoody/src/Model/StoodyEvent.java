package Model;

import java.util.Date;

/**
 * An event object named (StoodyEvent) to differentiate between a language event.
 *
 */
public class StoodyEvent {
	
	private int id;
	private eEventType eventType;
	private String title;
	private Date startDateTime;
	private Date endDateTime;
	private String location;

	
	
	public StoodyEvent(eEventType eventType, String title, Date startDateTime, Date endDateTime, String location) {
		this.id = -1;
		this.eventType = eventType;
		this.title = title;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.location = location;
	}

	public StoodyEvent(int id, eEventType eventType, String title, Date startDateTime, Date endDateTime,
			String location) {
		this.id = id;
		this.eventType = eventType;
		this.title = title;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.location = location;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public eEventType getEventType() {
		return eventType;
	}
	public void setEventType(eEventType eventType) {
		this.eventType = eventType;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}

/*
 * 
 * 	final static String CREATE_EVENT_TABLE = 
			"CREATE TABLE \"event\" (\n" + 
					"  \"event_id\"	INTEGER PRIMARY KEY AUTOINCREMENT,\n" + 
					"	\"event_type\"	INTEGER NOT NULL,\n" + 
					"	\"title\"	TEXT NOT NULL,\n" + 
					"	\"start_date_time\"	TEXT NOT NULL,\n" + 
					"	\"end_date_time\"	TEXT NOT NULL,\n" + 
					"	\"location\"	TEXT NOT NULL\n" + 
					");";
 * 
 * */
 