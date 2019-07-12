package View;

import java.awt.event.*;

import javax.swing.*;

import Model.DataLayer;
import Model.eUserType;

public class ViewLogin {

	public static JFrame DisplayView() {
		// add frame 
		JFrame frame = new JFrame("Login");  
		
		// add labels
		final JLabel usernameLabel = new JLabel("Username");
		final JLabel passwordLabel = new JLabel("Password");

		// add text fields
		final JTextField usernameTextField =new JTextField();
		final JTextField passwordTextField =new JTextField();

		// add button
		JButton loginButton=new JButton("Login");  

		// set bounds
		usernameLabel.setBounds(50,30, 200,20);  
		usernameTextField.setBounds(50,50, 200,20);  
		passwordLabel.setBounds(50,80, 200,20);
		passwordTextField.setBounds(50,100, 200,20);
		loginButton.setBounds(50,250,95,30);  
		    
		
		// add items to frame
		frame.add(loginButton);
	    frame.add(usernameTextField);
	    frame.add(passwordTextField);
	    frame.add(usernameLabel);
	    frame.add(passwordLabel);
	    frame.setSize(400,400);  
	    frame.setLayout(null);  
	    frame.setVisible(true);
	    
		// login click event
		ActionListener loginClick =	new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				eUserType userType = DataLayer.Login(usernameTextField.getText(), passwordTextField.getText());
				
				
				// show add users window if the user is an administrator
				if (userType == eUserType.administrator) 
				{
					frame.dispose();
					//frame.setVisible(false);
					ViewAddUser.DisplayView();
				}
	        }  
		};
		
		// add event listener
		loginButton.addActionListener(loginClick);
		     
		return frame;
	}
}
