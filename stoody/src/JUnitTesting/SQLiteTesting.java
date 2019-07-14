package JUnitTesting;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.SQLiteDataLayer;
import Model.StoodyEvent;
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
		   
		   String startDateStr = "2019-01-01 19:00";
		   DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		   Date startDate = new Date();
		   try {
			   startDate = format.parse(startDateStr);
		   } catch (ParseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   
		   StoodyEvent event = new StoodyEvent(eEventType.course, "Math2w2", startDate, startDate, "21");
		   
		   
		   boolean tablesExist = SQLiteDataLayer.AddEvent(event, 122);
		   
		   // assert
		   assertTrue(tablesExist);

	   }
}
