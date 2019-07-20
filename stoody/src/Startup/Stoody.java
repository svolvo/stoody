package Controller;
import java.awt.event.*;

import javax.swing.*;

import Model.DataLayer;
import Model.SQLiteDataLayer;
import View.*;

public class Stoody {
	
	public static void main(String[] args) {
		
		 // Data persistence 
	     SQLiteDataLayer.createTablesIfRequired();
	     
	     // display login view
	     Login();
	     
	}
	
	
	/**
	 * Displays the login view 
	 */
	public static void Login() {
		
		ViewLogin.DisplayView();
		
		//DataLayer.getInstance().Login("222", "222");
		
		
		//ViewAddNewCourse.DisplayView();
		//ViewSignUp.DisplayView();
		//ViewChangePassword.DisplayView();
		//ViewAddNewMeeting.DisplayView();

		//ViewDisplayEvents.DisplayView();
	}
	

 

}
