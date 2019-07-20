package View;

import java.awt.event.*;

import javax.swing.*;

import Model.*;


public class ViewAddUser {

	public static JFrame DisplayView() {
		
		
		JFrame frame = new JFrame("Add User");  
		
		// add labels
		final JLabel idLabel = new JLabel("ID");
		final JLabel firstNameLabel = new JLabel("First Name");
		final JLabel lastNameLabel = new JLabel("Last Name");
		final JLabel isTeacherLabel = new JLabel("Is Teacher");
		
		// add text fields
		final JTextField idTextField =new JTextField();
		final JTextField firstNameTextField =new JTextField();
		final JTextField lastNameTextField =new JTextField();
		final JCheckBox isteacherCheckBox = new JCheckBox(); 
		
		// add button
		JButton addUserButton = new JButton("Add User");  

		// set bounds
		idLabel		.setBounds(50,30, 200,20);  
		idTextField	.setBounds(50,50, 200,20);  
		firstNameLabel.setBounds(50,80, 200,20);
		firstNameTextField.setBounds(50,100, 200,20);
		lastNameLabel.setBounds(50,130, 200,20);  
		lastNameTextField.setBounds(50,150, 200,20);  
		isTeacherLabel.setBounds(50,180, 200,20);
		isteacherCheckBox.setBounds(50,200, 200,20);
		
		
		
		addUserButton.setBounds(50,250,95,30);  
		
		// add items to frame
	    frame.add(idLabel);
	    frame.add(firstNameLabel);
	    frame.add(lastNameLabel);
	    frame.add(isTeacherLabel);
	    frame.add(idTextField);
	    frame.add(firstNameTextField);
	    frame.add(lastNameTextField);
	    frame.add(isteacherCheckBox);
	    frame.add(addUserButton);
	    
	    // add logout option
	    JButton logout = new JButton("Logout");  
	    logout.setBounds(50, 300, 95, 30);    
	    frame.add(logout);	    
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				ViewLogin.DisplayView();
			}
		});
	    
	    frame.setSize(400,400);  
	    frame.setLayout(null);  
	    frame.setVisible(true);  
		
		// add event listener
		addUserButton.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){
			// Add user click event
			
			boolean success = false;
			try
			{
				if (!isteacherCheckBox.isSelected())
				{
				success = DataLayer.getInstance().AddNewRegularUser(new Student(Integer.parseInt(idTextField.getText()), firstNameTextField.getText(), lastNameTextField.getText()));
				}
				else {
					success = DataLayer.getInstance().AddNewRegularUser(new Teacher(Integer.parseInt(idTextField.getText()), firstNameTextField.getText(), lastNameTextField.getText()));
				}
		
			} 
			catch (Exception ex) {
				
			}
			
			// show Admin that the user was added or not
			if (success)
			{
				JOptionPane.showMessageDialog(frame,
					    "Added User Successfully.",
					    "",
					    JOptionPane.PLAIN_MESSAGE);
				
				// clear text fields.
				idTextField.setText("");
				firstNameTextField.setText("");
				idTextField.setText("");
				lastNameTextField.setText("");
				isteacherCheckBox.setSelected(false);
			}
			else {
				JOptionPane.showMessageDialog(frame,
					    "Oops, Something went wrong make sure the inputs are corret",
					    "",
					    JOptionPane.PLAIN_MESSAGE);
				}
        	}  
		});  
		     
		
	
		return frame;
	}
}
