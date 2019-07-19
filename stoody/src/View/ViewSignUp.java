package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.DataLayer;
import Model.StoodyEvent;
import Model.eEventType;
import Model.CourseDetails;
import Model.eUserCourseStatus;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Model.DataLayer;
import Model.StoodyEvent;


public class ViewSignUp {
	

    public static JFrame DisplayView() {
    	
    	
		JFrame frame = new JFrame("sign up course");
		JLabel titleLabel = new JLabel ("Course registration");
		titleLabel.setFont(new Font("devid", Font.PLAIN, 21));
		titleLabel.setBounds(350,40,200,24);
		frame.add(titleLabel);

		String columnNames[] = { "Name",
				"Day",
				"MOED A",
				"MOED B",
				"Status"
		};
		 int courseAmount = 0 ;
		 ArrayList<CourseDetails> courseList = DataLayer.getInstance().getCourseList();
		 courseAmount = courseList.size();
		 
		 Object[][] data = new Object[courseAmount][5];
		 
		 
		 for(int i = 0; i < data.length;i++) 
		 {
			data[i][0] = courseList.get(i).get_course().get_courseName();
			data[i][1] = courseList.get(i).get_course().get_courseDay();
			data[i][2] = courseList.get(i).get_course().get_moedA_Start();
			data[i][3] = courseList.get(i).get_course().get_moedB_Start();
			data[i][4]=courseList.get(i).get_status(); 
		 }
		 
		 TableModel model = new DefaultTableModel(data, columnNames){
			 public boolean isCellEditable(int row, int column)
			 {
			 return false;//This causes all cells to be not editable
			 }
		 };
		 
		 JTable table = new JTable(model);
		 table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		 table.setFont(new Font("Verdana", Font.PLAIN, 14));

		 JScrollPane pane = new JScrollPane(table);
		pane.setBounds(50,100,800,450);	    
		frame.add(pane);
		 JButton[] signUp= new JButton[courseAmount];
		 
		 for(int i = 0; i < data.length;i++) 
		 {
			 table.setRowHeight(i, 40);
			 if (data[i][4] == eUserCourseStatus.unsubscribed)
			 {
				 signUp[i] = new JButton("Sign UP");
				 signUp[i].setBounds(860, i*(40)+120 , 90 , 40);
				 frame.add(signUp[i]);
			 }
		 }
		 
		 frame.setSize(1000,650);  
		 frame.setLayout(null); 
		 frame.setVisible(true); 
		 
		 
		 for (int i = 0 ; i < courseAmount ; i ++ )
		 {
			 final int j = i;
			 signUp[i].addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					 
					
					CourseDetails C = new CourseDetails( courseList.get(j).get_courseId() , courseList.get(j).get_course(), courseList.get(j).get_status() );
					 
					 
					 if(DataLayer.getInstance().SignUpToCourse(C))
					 {
						 JOptionPane.showMessageDialog(frame,
								    "sign Up course Successfully.",
								    "",
								    JOptionPane.PLAIN_MESSAGE);
					 }
					 else
					 {
						 JOptionPane.showMessageDialog(frame,
								    "ERROR, choose another course with differnt details.",
								    "",
								    JOptionPane.PLAIN_MESSAGE);
					 
					 }
				 }
			 });
		 }
		return frame;
    }

}
















