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
		newPasswordLabel		.setBounds(50,30, 400,20);  
		newPasswordTextField	.setBounds(50,60, 200,20);  
		
		
		newPasswordButton.setBounds(50,120,150,30);  
		    
		
		// add items to frame
	    frame.add(newPasswordLabel);
	    frame.add(newPasswordTextField);
	   frame.add(newPasswordButton);

	    
	    frame.setSize(500,220);  
	    frame.setLayout(null);  
	    frame.setVisible(true);  
	    
	    
	 // add event listener
	 	newPasswordButton.addActionListener(new ActionListener(){  
	 	public void actionPerformed(ActionEvent e){
	 		
	 		String _password;
	 		_password = newPasswordTextField.getText().toString();
	 		boolean success = DataLayer.getInstance().ChangePassword(_password); //Checks if a password is valid	
	 		
	 		if (!(success))
	 		{
	 			
	 			JOptionPane.showMessageDialog(frame,
				    "ERROR, Please enter a password over 8 char and not your ID. Try again.",
				    "",
				    JOptionPane.PLAIN_MESSAGE);
	 		
	 		}
	 		else
	 		{
	 			//int input = JOptionPane.showOptionDialog(frame,
				//    "Change password Successfully",
				//    "",
				//    JOptionPane.PLAIN_MESSAGE);
	 			
	 			
	 			int input = JOptionPane.showOptionDialog(frame, "Change password Successfully", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

	 			if(input == JOptionPane.OK_OPTION)
	 			{
	 			    
	 				frame.dispose();
	 				ViewLogin.DisplayView();
	 				// do something
	 			}
	 		}
	 	}  
		});  
	 	
		return frame;
	}
}