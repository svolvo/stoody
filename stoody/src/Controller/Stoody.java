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
		
		//DataLayer.get_Instance().Login("122", "122");
		
		//ViewAddNewCourse.DisplayView();
		//ViewChangePassword.DisplayView();
		//ViewAddNewMeeting.DisplayView();

		//ViewDisplayEvents.DisplayView();
	}
	

 

}
