package View;


import java.awt.event.*;

import javax.swing.*;

import Model.DataLayer;
import Model.Student;

public class ViewChangePassword {
	

	public static JFrame DisplayView() {
		
		
		JFrame frame = new JFrame("Change Password");  
		
		// add labels
		final JLabel newPasswordLabel = new JLabel("Enter a new password over 8 char (NOT YOUR ID!)");

		
		// add text fields
		final JTextField newPasswordTextField =new JTextField();

		// add button
		JButton newPasswordButton = new JButton("Save");  

		// set bounds
		newPasswordLabel		.setBounds(50,30, 300,20);  
		newPasswordTextField	.setBounds(50,60, 200,20);  
		
		
		newPasswordButton.setBounds(50,120,150,30);  
		    
		
		// add items to frame
	    frame.add(newPasswordLabel);
	    frame.add(newPasswordTextField);
	   frame.add(newPasswordButton);

	    
	    frame.setSize(400,220);  
	    frame.setLayout(null);  
	    frame.setVisible(true);  
	    
	    
	 // add event listener
	 	newPasswordButton.addActionListener(new ActionListener(){  
	 	public void actionPerformed(ActionEvent e){
	 // Add user click event
	 		
	 		
	 		String _password;
	 		_password = newPasswordTextField.getText().toString();
	 		boolean success = DataLayer.get_Instance().ChagngePassword(_password);	 		
	 		
	 		if (!(success))
	 		{
	 			
	 			JOptionPane.showMessageDialog(frame,
				    "ERROR, Please enter a password over 8 char and not your ID. Try again.",
				    "",
				    JOptionPane.PLAIN_MESSAGE);
	 		
	 		}
	 		else
	 		{
	 			JOptionPane.showMessageDialog(frame,
				    "Change password Successfully",
				    "",
				    JOptionPane.PLAIN_MESSAGE);
	 		}
	 	}  
		});  
	 	
		return frame;
	}
}
