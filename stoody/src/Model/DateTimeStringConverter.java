package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeStringConverter {

	
	public static String DateToStringGetDisplayTime(Date date)
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");  
		return dateFormat.format(date);  
	}
}
