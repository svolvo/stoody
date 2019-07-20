package JUnitTesting;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.SQLiteDataLayer;
import Model.StoodyEvent;
import Model.eEventStatus;
import Model.eEventType;


public class SQLiteTesting {
	
	
	   @Test
	   public void CheckIfTableExists() {	  
		   // act
		   boolean tablesExist = SQLiteDataLayer.CheckIfTablesExist();
		   
		   // assert
		   assertTrue(tablesExist);

	   }

	   
	   @Test
	   public void AddEvent() {	  
		   // act
		   
		   String startDateStr = "2019-07-01 17:00";
		   DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		   Date startDate = new Date();
		   try {
			   startDate = format.parse(startDateStr);
		   } catch (ParseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   
		   StoodyEvent event = new StoodyEvent(eEventType.course, "English2", startDate, startDate, "21");
		   
		   
		   boolean tablesExist = SQLiteDataLayer.AddEvent(event, 333);
		   
		   // assert
		   assertTrue(tablesExist);

	   }
	   
	   @Test
	   public void GetEventByDate() {	  
		   // act
		   
		   String startDateStr = "2019-07-01 19:00";
		   DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		   Date startDate = new Date();
		   try {
			   startDate = format.parse(startDateStr);
		   } catch (ParseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   
		   int userId = 122;
		   
		   StoodyEvent event = new StoodyEvent(eEventType.course, "Math2w2", startDate, startDate, "21");
		   
		   
		   ArrayList<StoodyEvent> events = SQLiteDataLayer.GetEventsListByDate(userId, startDate);
		   
		   
		   // assert
		   assertTrue(events != null);

	   }
}
