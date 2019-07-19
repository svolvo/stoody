package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	
	public static String DateToStringGetDisplayTime(Date date)
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");  
		return dateFormat.format(date);  
	}
	
	public static String GetDateTimeByStringFormat(Date date)
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		return dateFormat.format(date);  
	}
	
	public static String GetShortDateString(Date date)
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		return dateFormat.format(date);  
	}
	
	public static Date GetDateFromString(String dateStr)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startDate = new Date();
		try {
			   return format.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}	
	
	public static Date AddDays(Date current, int days)
	{
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
	}
	
	public static Date AddTime(Date original, int hours, int minutes)
	{
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(original);
        calendar.add(Calendar.HOUR, hours);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
	}
}
