package JUnitTesting;
import org.junit.Test;
import static org.junit.Assert.*;

import Model.SQLiteDataLayer;


public class SQLiteTesting {
	
	
	   @Test
	   public void CheckIfTableExists() {	  
		   // act
		   boolean tablesExist = SQLiteDataLayer.CheckIfTablesExist();
		   
		   // assert
		   assertTrue(tablesExist);

	   }

}
