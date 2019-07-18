package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		final JLabel dateLabel = new JLabel("Date");
		final JLabel timeLabel = new JLabel("Time");

		// add text fields
		final JTextField titleTextField =new JTextField();
		final JTextField locationTextField =new JTextField();
		
		// add button
		JButton saveAllButton = new JButton("Save");
		
		
		// date picker //
		
		UtilDateModel model = new UtilDateModel();
		
		//properties of the date picker
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		
		//date picker //
		
		// time picker //
		
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		
		// time picker //
		
		// Add Users
		
		// Pop up List of the Users //
		
		String[] columnNames = {"Name",
				"Last Name",
				"Type",
				"Marked"
				};
		int eventAmount = 0;//need to find how much event we have on date that the user pick
		
		eventList = DataLayer.get_Instance().GetReuglarUsersList();
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
		pane.setBounds(50,250,570,250);
		
		frameEventCreate.add(pane);
		
		
		// Pop up List of the Users //
		  
		frameEventCreate.setLayout(null);  
		frameEventCreate.setVisible(true); 
		
		// save button //

		
		saveAllButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				if(!titleTextField.getText().toString().equals("") &&
					!locationTextField.getText().toString().equals("")&&
					datePicker.getModel().getValue() != null) {
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
				Date date = (Date) datePicker.getModel().getValue();
				Date time = (Date) timeSpinner.getModel().getValue();
				date.setHours(time.getHours());
				date.setMinutes(time.getMinutes());
				date.setSeconds(time.getSeconds());
				
				StoodyEvent event = new StoodyEvent(eEventType.user_meeting,title,date,date,location);
				
				System.out.println(date);
				System.out.println();
				DataLayer.get_Instance().AddMeeting(event,userId);//need to add func AddMeeting
				
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Israel"));
				
				titleTextField.setText("");
				locationTextField.setText("");
				datePicker.getModel().setDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
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
		dateLabel.setBounds(50,130,200,40);
		datePicker.setBounds(50,160, 200,40);
		timeSpinner.setBounds(300, 160, 200,30);
		timeLabel.setBounds(300,140,200,20);
		saveAllButton.setBounds(50,600,200,20);
		
		
		frameEventCreate.add(windowLabel);
		frameEventCreate.add(titleLabel);
		frameEventCreate.add(locationLabel);
		frameEventCreate.add(titleTextField);
		frameEventCreate.add(locationTextField);
		frameEventCreate.add(dateLabel);
		frameEventCreate.add(datePicker);
		frameEventCreate.add(timeSpinner);
		frameEventCreate.add(timeLabel);
		frameEventCreate.add(saveAllButton);
		
		
		frameEventCreate.setSize(800,800);  
		frameEventCreate.setLayout(null);  
		frameEventCreate.setVisible(true);
		
		return frameEventCreate;
	}
}
