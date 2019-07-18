
package View;

import java.awt.Font;
import java.awt.event.*;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Model.DataLayer;
import Model.Student;
import Model.Course;

public class ViewAddNewCourse {
	
	
		public static JFrame DisplayView() {
			
			
			JFrame frame = new JFrame("Add course");  
			
			UtilDateModel model1 = new UtilDateModel();
			UtilDateModel model2 = new UtilDateModel();
			UtilDateModel modelA = new UtilDateModel();
			UtilDateModel modelB = new UtilDateModel();
			

			//properties of the date picker
			Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");
			
			
			JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
			JDatePickerImpl datePickerStart = new JDatePickerImpl(datePanel1, new DateComponentFormatter());
			JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
			JDatePickerImpl datePickerEnd = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
			JDatePanelImpl datePanelA = new JDatePanelImpl(modelA, p);
			JDatePickerImpl datePickerMoedA = new JDatePickerImpl(datePanelA, new DateComponentFormatter());
			JDatePanelImpl datePanelB = new JDatePanelImpl(modelB, p);
			JDatePickerImpl datePickerMoedB = new JDatePickerImpl(datePanelB, new DateComponentFormatter());

			
			JSpinner timeSpinnerStart = new JSpinner( new SpinnerDateModel() );
			JSpinner timeSpinnerEnd = new JSpinner( new SpinnerDateModel() );
			JSpinner timeSpinnerStartA = new JSpinner( new SpinnerDateModel() );
			JSpinner timeSpinnerEndA = new JSpinner( new SpinnerDateModel() );
			JSpinner timeSpinnerStartB = new JSpinner( new SpinnerDateModel() );
			JSpinner timeSpinnerEndB = new JSpinner( new SpinnerDateModel() );

			JSpinner.DateEditor timeEditorStart = new JSpinner.DateEditor(timeSpinnerStart, "HH:mm:ss");
			JSpinner.DateEditor timeEditorEnd = new JSpinner.DateEditor(timeSpinnerEnd, "HH:mm:ss");
			JSpinner.DateEditor timeEditorStartA = new JSpinner.DateEditor(timeSpinnerStartA, "HH:mm:ss");
			JSpinner.DateEditor timeEditorStartB = new JSpinner.DateEditor(timeSpinnerStartB, "HH:mm:ss");
			JSpinner.DateEditor timeEditorEndA = new JSpinner.DateEditor(timeSpinnerEndA, "HH:mm:ss");
			JSpinner.DateEditor timeEditorEndB = new JSpinner.DateEditor(timeSpinnerEndB, "HH:mm:ss");

			
			timeSpinnerStart.setEditor(timeEditorStart);
			timeSpinnerEnd.setEditor(timeEditorEnd);
			timeSpinnerStartA.setEditor(timeEditorStartA);
			timeSpinnerEndA.setEditor(timeEditorEndA);
			timeSpinnerStartB.setEditor(timeEditorStartB);
			timeSpinnerEndB.setEditor(timeEditorEndB);


					
			// add labels
			final JLabel detailsCourseLabel = new JLabel("Details Course");
			final JLabel nameLabel = new JLabel("Course name");
			final JLabel dayLabel = new JLabel("Day");
			final JLabel dateStartLabel = new JLabel("Date start");
			final JLabel timeStartLabel = new JLabel("Time start");
			final JLabel dateEndLabel = new JLabel("Date end");
			final JLabel timeEndLabel = new JLabel("Time end");
			final JLabel locationLabel = new JLabel("location");
			
			final JLabel detailsTestALabel = new JLabel("Test - MOED A");
			final JLabel dateTestALabel = new JLabel("Date");
			final JLabel timeStartTestALabel = new JLabel("Time Start");
			final JLabel timeEndTestALabel = new JLabel("Time End");
			final JLabel locationTestALabel = new JLabel("location");
			
			final JLabel detailsTestBLabel = new JLabel("Test - MOED B");
			final JLabel dateTestBLabel = new JLabel("Date");
			final JLabel timeStartTestBLabel = new JLabel("Time Start");
			final JLabel timeEndTestBLabel = new JLabel("Time Start");
			final JLabel locationTestBLabel = new JLabel("location");
			
			// add text fields
			final JTextField nameTextField =new JTextField();
			final JTextField dayTextField =new JTextField();
			final JTextField locationTextField =new JTextField();
			final JTextField locationTestATextField =new JTextField();
			final JTextField locationTestBTextField =new JTextField();
			
			// add button
			JButton addCourseButton = new JButton("Add course");  

			// set bounds
			detailsTestALabel.setBounds(400, 30,200,20);
			dateTestALabel.setBounds(400, 70,200,20);
			datePickerMoedA.setBounds(400, 90 , 200, 20);
			timeStartTestALabel.setBounds(400, 120,200,20);
			timeSpinnerStartA.setBounds(400, 140,200,20);
			timeEndTestALabel.setBounds(400, 170,200,20);
			timeSpinnerEndA.setBounds(400, 190,200,20);
			locationTestALabel.setBounds(400, 220,200,20);
			locationTestATextField.setBounds(400, 250,200,20);
			
			detailsTestBLabel.setBounds(400,290 ,200,20);
			dateTestBLabel.setBounds(400, 320,200,20);
			datePickerMoedB.setBounds(400, 340 , 200, 20);
			timeStartTestBLabel.setBounds(400, 370,200,20);
			timeSpinnerStartB.setBounds(400, 390,200,20);
			timeEndTestBLabel.setBounds(400, 420,200,20);
			timeSpinnerEndB.setBounds(400, 440,200,20);
			locationTestBLabel.setBounds(400, 470,200,20);
			locationTestBTextField.setBounds(400, 490,200,20);
			
			
			detailsCourseLabel.setBounds(50,30,200,20);
			nameLabel		.setBounds(50,70, 200,20);  
			nameTextField	.setBounds(50,90, 200,20);  
			dayLabel.setBounds(50, 120, 200, 20);
			dayTextField.setBounds(50, 140, 200, 20);
			dateStartLabel.setBounds(50,170, 200,20);
			datePickerStart.setBounds(50,190, 200,20);
			timeStartLabel.setBounds(50,220, 200,20);  
			timeSpinnerStart.setBounds(50,240, 200,20);  
			dateEndLabel.setBounds(50,270, 200,20);
			datePickerEnd.setBounds(50,290, 200,20);
			timeEndLabel.setBounds(50,320, 200,20);  
			timeSpinnerEnd.setBounds(50,340, 200,20); 
			locationLabel.setBounds(50, 370 , 200, 20);
			locationTextField.setBounds(50, 390, 200, 20);			
			
			addCourseButton.setBounds(50,450,120,30);  
			detailsCourseLabel.setFont(new Font("devid", Font.PLAIN, 21));
			detailsTestALabel.setFont(new Font("devid", Font.PLAIN, 16));
			detailsTestBLabel.setFont(new Font("devid", Font.PLAIN, 16));

		
			
			// add items to frame
		    frame.add(nameLabel);
		    frame.add(dayLabel);
		    frame.add(dayTextField);
		    frame.add(dateStartLabel);
		    frame.add(timeStartLabel);
		    frame.add(dateEndLabel);
		    frame.add(timeEndLabel);
		    frame.add(locationLabel);
		    frame.add(nameTextField);
		    frame.add(datePickerStart);
		    frame.add(timeSpinnerStart);
		    frame.add(datePickerEnd);
		    frame.add(timeSpinnerEnd);
		    frame.add(locationTextField);
		   frame.add(addCourseButton);
		   frame.add(detailsCourseLabel);
		   frame.add(detailsTestALabel);
		   frame.add(detailsTestBLabel);
		   frame.add(dateTestALabel);
		   frame.add(datePickerMoedA);
		   frame.add(timeStartTestALabel);
		   frame.add(locationTestALabel);
		   frame.add(dateTestBLabel);
		   frame.add(datePickerMoedB);
		   frame.add(timeStartTestBLabel);
		   frame.add(locationTestBLabel);
		   frame.add(timeSpinnerStartA);
		   frame.add(locationTestATextField);
		   frame.add(timeSpinnerStartB);
		   frame.add(locationTestBTextField);
		   frame.add(timeSpinnerEndA);
		   frame.add(timeSpinnerEndB);
		   frame.add(timeEndTestALabel);
		   frame.add(timeEndTestBLabel);
		   
		    frame.setSize(700,600);  
		    frame.setLayout(null);  
		    frame.setVisible(true);  
		    
		    
		    
		    
		    addCourseButton.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){
					// Add user click event
					
					Course C = new Course();
					Date _start, _end, _startA, _startB, _endA, _endB;
					
					C._courseName = nameTextField.getText().toString();
					C._start= (Date)datePickerStart.getModel().getValue();
					_start = (Date)timeSpinnerStart.getModel().getValue();
					C._start.setHours(_start.getHours());
					C._start.setMinutes(_start.getMinutes());
					C._start.setSeconds(_start.getSeconds());
					C._end= (Date)datePickerEnd.getModel().getValue();
					_end = (Date)timeSpinnerEnd.getModel().getValue();
					C._end.setHours(_end.getHours());
					C._end.setMinutes(_end.getMinutes());
					C._end.setSeconds(_end.getSeconds());
					C._location= locationTextField.getText().toString();
					C._dayOfTheWeek= dayTextField.getText().toString();
					C.moedA_Start=(Date)datePickerMoedA.getModel().getValue();
					_startA = (Date)timeSpinnerStartA.getModel().getValue();
					C.moedA_Start.setHours(_startA.getHours());
					C.moedA_Start.setMinutes(_startA.getMinutes());
					C.moedA_Start.setSeconds(_startA.getSeconds());
					C.moedA_End=(Date)datePickerMoedA.getModel().getValue();
					_endA = (Date)timeSpinnerEndA.getModel().getValue();
					C.moedA_End.setHours(_endA.getHours());
					C.moedA_End.setMinutes(_endA.getMinutes());
					C.moedA_End.setSeconds(_endA.getSeconds());
					C._locationA= locationTestATextField.getText().toString();	
					C.moedB_Start=(Date)datePickerMoedB.getModel().getValue();
					_startB = (Date)timeSpinnerStartB.getModel().getValue();
					C.moedB_Start.setHours(_startB.getHours());
					C.moedB_Start.setMinutes(_startB.getMinutes());
					C.moedB_Start.setSeconds(_startB.getSeconds());
					C.moedB_End=(Date)datePickerMoedB.getModel().getValue();
					_endB = (Date)timeSpinnerEndB.getModel().getValue();
					C.moedB_End.setHours(_endB.getHours());
					C.moedB_End.setMinutes(_endB.getMinutes());
					C.moedB_End.setSeconds(_endB.getSeconds());
					C._locationB= locationTestBTextField.getText().toString();
							
					
					if (DataLayer.get_Instance().AddCourse(C))
					{
						JOptionPane.showMessageDialog(frame,
							    "Added course Successfully.",
							    "",
							    JOptionPane.PLAIN_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(frame,
							    "Oops, exist another event in this time. Try again with differnt details.",
							    "",
							    JOptionPane.PLAIN_MESSAGE);
					}
				}
		    	
		    });
			
			return frame;
		}
}



