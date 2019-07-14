package View;

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

public class ViewDisplayEvents 
{  
    //lists 
    public static int counter = 0;
	
    public static JFrame DisplayView() {
		
		
		JFrame frame1 = new JFrame("choose date");
		JFrame frame2 = new JFrame("Event List");
		
		UtilDateModel model = new UtilDateModel();
		
		//properties of the date picker
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		String [] data = {"1", "2", "3", "4", "5", "6", "7"};
		
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		
		// add labels
		final JLabel idLabel = new JLabel("Date");
		
		// add button
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(50,250,95,30);
		frame1.add(searchButton);

		// set bounds
		idLabel		.setBounds(50,30, 200,20);  
		datePicker.setBounds(50,50, 200,40);
		//panel.setBounds(-50, 80, 200, 20 * 5);
		    
		
		// add items to frame
	    frame1.add(idLabel);
	    
	    frame1.add(datePicker);
	    
	    frame1.setSize(400,400);  
	    frame1.setLayout(null);  
	    frame1.setVisible(true);
	    
	    searchButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				// search for events
				
				Date selectedDate = (Date) datePicker.getModel().getValue();
			    if(selectedDate != null) 
			    {
			    	
			    	String[] columnNames = {"Type",
	                        				"Title",
	                        				"Start date",
	                        				"End Date",
	                        				"Location"};
			    	int eventAmount = 0;//need to find how much event we have on date that the user pick
			    	
			    	
			    	ArrayList<StoodyEvent> eventList = DataLayer.get_Instance().GetEventsListByDate(selectedDate);
			    	eventAmount = eventList.size();
		
			    	//this code we use for putting the event to the list and after that we show the list
			    	Object[][]data = new Object[eventAmount][5];
			    	
			    	for(int i = 0; i < data.length;i++) 
			    	{
			    		for(int j = 0; j < data[0].length;j++) 
			    		{
			    			data[i][j] = eventList.get(i).getEventType();
			    		}
			    	}
			    	
			    	
			    	 TableModel model = new DefaultTableModel(data, columnNames)
			    	  {
			    	    public boolean isCellEditable(int row, int column)
			    	    {
			    	      return false;//This causes all cells to be not editable
			    	    }
			    	  };
			    	  JTable table = new JTable(model);
			    	
					//JTable table = new JTable(data, columnNames);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					table.setFont(new Font("Verdana", Font.PLAIN, 24));
					
					for(int i = 0; i < data.length;i++) 
					{
						table.setRowHeight(i, 40);
					}
					
					frame2.add(new JScrollPane(table));
			         
			        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        frame2.setTitle("JList Example");
			        frame2.setSize(400,400);
			        frame2.setLocationRelativeTo(null);
			        frame2.setVisible(true);
			        
			    }
				
	        	}  
			});
		     
		return frame1;
	}

}
