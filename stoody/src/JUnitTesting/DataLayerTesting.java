package JUnitTesting;
import org.junit.Test;

import Model.DataLayer;
import Model.eUserType;

import static org.junit.Assert.*;

public class DataLayerTesting {
	
	@Test
	public void LoginAdmin() {
		// act 
		eUserType userType = DataLayer.getInstance().Login("Admin", "HITadmin1234");
		
		// assert
		assertEquals(userType, eUserType.administrator);
	}

}
