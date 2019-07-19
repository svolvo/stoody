package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Model.DataLayer;
import Model.RegularUser;
import Model.StoodyEvent;
import Model.eEventType;

public class ViewAddNewMeeting
{
	private static Object[][]data;
	private static ArrayList<RegularUser> eventList;
	private static JTable table;
	
	public static JFrame DisplayView() 
	{	
		JFrame frameEventCreate = new JFrame("Meeting event create");
		
		
		// add labels
		final JLabel titleLabel = new JLabel("Title");
		final JLabel locationLabel = new JLabel("Location");
		final JLabel windowLabel = new JLabel("Meeting Event Add");
		final JLabel dateStartLabel = new JLabel("Date Start");
		final JLabel timeStartLabel = new JLabel("Time Start");
		final JLabel dateFinishLabel = new JLabel("Date Finish");
		final JLabel timeFinishLabel = new JLabel("Time finish");

		// add text fields
		final JTextField titleTextField =new JTextField();
		final JTextField locationTextField =new JTextField();
		
		// add button
		JButton saveAllButton = new JButton("Save");
		
		
		// date picker //
		
		UtilDateModel modelStart = new UtilDateModel();
		UtilDateModel modelFinish = new UtilDateModel();
		
		
		//properties of the date picker
		Properties pStart = new Properties();
		pStart.put("text.today", "Today");
		pStart.put("text.month", "Month");
		pStart.put("text.year", "Year");
		
		//properties of the date picker
		Properties pFinish = new Properties();
		pFinish.put("text.today", "Today");
		pFinish.put("text.month", "Month");
		pFinish.put("text.year", "Year");
		
		
		
		JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStart, pStart);
		JDatePanelImpl datePanelFinish = new JDatePanelImpl(modelFinish, pFinish);
		
		JDatePickerImpl datePickerStart = new JDatePickerImpl(datePanelStart, new DateComponentFormatter());
		JDatePickerImpl datePickerFinish = new JDatePickerImpl(datePanelFinish, new DateComponentFormatter());
		
		//date picker //
		
		// time picker //
		
		JSpinner timeSpinnerStart = new JSpinner( new SpinnerDateModel() );
		JSpinner timeSpinnerFinish = new JSpinner(new SpinnerDateModel());
		
		JSpinner.DateEditor timeEditorStart = new JSpinner.DateEditor(timeSpinnerStart, "HH:mm");
		JSpinner.DateEditor timeEdotrFinish = new JSpinner.DateEditor(timeSpinnerFinish,"HH:mm" );
		
		timeSpinnerStart.setEditor(timeEditorStart);
		timeSpinnerFinish.setEditor(timeEdotrFinish);
		
		
		// time picker //
		
		// Add Users
		
		// Pop up List of the Users //
		
		String[] columnNames = {"Name",
				"Last Name",
				"Type",
				"Marked"
				};
		int eventAmount = 0;//need to find how much event we have on date that the user pick
		
		eventList = DataLayer.getInstance().GetReuglarUsersList();
		eventAmount = eventList.size();
		
		//this code we use for putting the event to the list and after that we show the list
		data = new Object[eventAmount][5];
		
		for(int i = 0; i < data.length;i++) 
		{
		data[i][0] = eventList.get(i).get_firstName();
		data[i][1] = eventList.get(i).get_lastName();
		data[i][2] = eventList.get(i).get_userType();
		data[i][3] = false;
		}
		
		
		TableModel model1 = new DefaultTableModel(data, columnNames)
		{
			public boolean isCellEditable(int row, int column)
			{
			if(column == 3)
				return true;
			else
				return false;//This causes all cells to be not editable
			}
		
		public Class<?> getColumnClass(int column) {
			switch (column) {
			case 0:
				return String.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			case 3:
				return Boolean.class;
			default:
				return String.class;
			}
		}
		
		};
		table = new JTable(model1);
		
		//JTable table = new JTable(data, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font("Verdana", Font.PLAIN, 24));
		
		for(int i = 0; i < data.length;i++) 
		{
		table.setRowHeight(i, 40);
		}
		
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(50,300,570,250);
		
		frameEventCreate.add(pane);
		
		
		// Pop up List of the Users //
		  
		frameEventCreate.setLayout(null);  
		frameEventCreate.setVisible(true); 
		
		// save button //

		
		saveAllButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				if(!titleTextField.getText().toString().equals("") &&
					!locationTextField.getText().toString().equals("")&&
					datePickerStart.getModel().getValue() != null &&
					datePickerFinish.getModel().getValue() != null) {
				// save //
				List<Integer> userId = new ArrayList<Integer>();
				
				for(int i = 0; i < data.length; i++) 
				{
					if(table.getModel().getValueAt(i,3).equals(true)) 
					{
						userId.add(eventList.get(i).get_id());
						data[i][3] = true;
						System.out.println("Marked - id: " + userId.get(userId.size() - 1));
					}
					else 
					{
						System.out.println("Not Marked");
					}
				}
				System.out.println();
				
				String title = titleTextField.getText().toString();
				String location = locationTextField.getText().toString();
				
				// Date start + time //
				
				Date dateStart = (Date) datePickerStart.getModel().getValue();
				Date timeStart = (Date) timeSpinnerStart.getModel().getValue();
				dateStart.setHours(timeStart.getHours());
				dateStart.setMinutes(timeStart.getMinutes());
				dateStart.setSeconds(timeStart.getSeconds());
				
				// Date start + time //
				
				// Date finish + time //
				
				Date dateFinish = (Date) datePickerStart.getModel().getValue();
				Date timeFinish = (Date) timeSpinnerStart.getModel().getValue();
				dateFinish.setHours(timeFinish.getHours());
				dateFinish.setMinutes(timeFinish.getMinutes());
				dateFinish.setSeconds(timeFinish.getSeconds());
				
				// Date finish + time //
				
				StoodyEvent event = new StoodyEvent(eEventType.user_meeting,title,dateStart,dateFinish,location);
				
				System.out.println(dateStart);
				System.out.println();
				DataLayer.getInstance().AddMeeting(event,userId);//need to add func AddMeeting
				
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Israel"));
				
				titleTextField.setText("");
				locationTextField.setText("");
				datePickerStart.getModel().setDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
				for(int i = 0; i < data.length; i++) 
				{
					data[i][3] = false;
				}
				
				frameEventCreate.dispose();
				ViewDisplayEvents.DisplayView();
				
				}
			}
			});
		
		
		windowLabel.setBounds(100,20,400,20);
		windowLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		
		titleLabel.setBounds(50,50,50,20);
		titleTextField.setBounds(50,70,200,20);
		locationLabel.setBounds(50,90,50,20);
		locationTextField.setBounds(50,110,200,20);
		dateStartLabel.setBounds(50,130,200,40);
		datePickerStart.setBounds(50,160, 200,30);
		dateFinishLabel.setBounds(50, 190 ,200 ,20);
		datePickerFinish.setBounds(50, 210, 200, 40);
		timeStartLabel.setBounds(300,140,200,20);
		timeSpinnerStart.setBounds(300, 160, 200,30);
		timeFinishLabel.setBounds(300, 190 , 200 ,20);
		timeSpinnerFinish.setBounds(300, 210, 200,30);
		saveAllButton.setBounds(50,600,200,20);
		
		
		frameEventCreate.add(windowLabel);
		frameEventCreate.add(titleLabel);
		frameEventCreate.add(locationLabel);
		frameEventCreate.add(titleTextField);
		frameEventCreate.add(locationTextField);
		frameEventCreate.add(dateStartLabel);
		frameEventCreate.add(datePickerStart);
		frameEventCreate.add(dateFinishLabel);
		frameEventCreate.add(datePickerFinish);
		frameEventCreate.add(timeSpinnerStart);
		frameEventCreate.add(timeStartLabel);
		frameEventCreate.add(timeFinishLabel);
		frameEventCreate.add(timeSpinnerFinish);
		frameEventCreate.add(saveAllButton);
		
		
		frameEventCreate.setSize(800,800);  
		frameEventCreate.setLayout(null);  
		frameEventCreate.setVisible(true);
		
		return frameEventCreate;
	}
}
