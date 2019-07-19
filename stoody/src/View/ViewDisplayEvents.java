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

import Model.*;

public class ViewDisplayEvents 
{  
    //lists 
    public static int counter = 0;
	
    public static JFrame DisplayView() {
		
		
		JFrame frame1 = new JFrame("choose date");
		JFrame framePopup = new JFrame("Full Information");
		
		UtilDateModel model = new UtilDateModel();
		
		//properties of the date picker
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		// frame Pop up //
		
		JLabel titleLabel = new JLabel("Title:");
		JLabel locationLabel = new JLabel("Location:");
		JLabel dateLabel = new JLabel("Start Time:");
		JLabel timeLabel = new JLabel("Finish Time:");
		
		JLabel titleText = new JLabel("Title");
		JLabel locationText = new JLabel("Location");
		JLabel dateText = new JLabel("Start Time");
		JLabel timeText = new JLabel("Finish Time");
		
		titleLabel.setBounds(20,20,200,20);
		locationLabel.setBounds(20,40,200,20);
		dateLabel.setBounds(20,60,200,20);
		timeLabel.setBounds(20,80,200,20);
		
		titleText.setBounds(120,20,200,20);
		locationText.setBounds(120,40,200,20);
		dateText.setBounds(120,60,200,20);
		timeText.setBounds(120,80,200,20);
		
		
		// frame Pop up //
		
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		
		// add labels
		final JLabel idLabel = new JLabel("Date");
		
		// add button
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(50,400,95,30);
		frame1.add(searchButton);
		
		JButton deleteButton = new JButton("Delet");
		JButton acceptButton = new JButton("Accept");
		
		deleteButton.setBounds(50,700,200,40);
		acceptButton.setBounds(300,700,200,40);

		// set bounds
		idLabel		.setBounds(50,30, 200,20);  
		datePicker.setBounds(50,50, 200,40);
		//panel.setBounds(-50, 80, 200, 20 * 5);
		    
		
		// add items to frame
	    frame1.add(idLabel);
	    
	    frame1.add(datePicker);
	    
	    frame1.setSize(1100,600);  
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
			    	ArrayList<StoodyEvent> eventList = DataLayer.getInstance().GetEventsListByDate(selectedDate);
			    	eventAmount = eventList.size();
			    	
			    	//this code we use for putting the event to the list and after that we show the list
			    	Object[][]data = new Object[eventAmount][5];
			    	
			    	for(int i = 0; i < data.length;i++) 
			    	{
			    		data[i][0] = eventList.get(i).getEventType();
			    		data[i][1] = eventList.get(i).getTitle();
			    		data[i][2] = DateHelper.DateToStringGetDisplayTime(eventList.get(i).getStartDateTime());
			    		data[i][3] = DateHelper.DateToStringGetDisplayTime(eventList.get(i).getEndDateTime());
			    		data[i][4] = eventList.get(i).getLocation();
			    	}
			    	
			    	
			    	 TableModel model = new DefaultTableModel(data, columnNames)
			    	  {
			    	    public boolean isCellEditable(int row, int column)
			    	    {
			    	      return false;//This causes all cells to be not editable
			    	    }
			    	  };
			    	  JTable table = new JTable(model);
			    	
					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					table.setFont(new Font("Verdana", Font.PLAIN, 14));
					
					for(int i = 0; i < data.length;i++) 
					{
						table.setRowHeight(i, 40);
					}
					
					JScrollPane pane = new JScrollPane(table);
					pane.setBounds(50,100,1000,200);
					
					frame1.add(pane);
					
					table.addMouseListener(new java.awt.event.MouseAdapter() {
					    @Override
					    public void mouseClicked(java.awt.event.MouseEvent evt) {
					        int row = table.rowAtPoint(evt.getPoint());
					        int col = table.columnAtPoint(evt.getPoint());
					        if (row >= 0 && col >= 0)
					        {
					        	String[] columnNamesPopup = {"Name",
                        				"Last Name",
                        				"Satus"};
						    	int userAmount = 0;//need to find how much event we have on date that the user pick
						    	ArrayList<EventParticipant> participantsList = DataLayer.getInstance().GetEventParticipants(eventList.get(row));
						    	userAmount = participantsList.size();
					
						    	//this code we use for putting the event to the list and after that we show the list
						    	Object[][]dataPopup = new Object[userAmount][5];
						    	
						    	for(int i = 0; i < dataPopup.length;i++) 
						    	{
						    		dataPopup[i][0] = participantsList.get(i).get_firstName();
						    		dataPopup[i][1] = participantsList.get(i).get_lastName();
						    		dataPopup[i][2] = participantsList.get(i).get_eventStatus();
						    	}
						    	
						    	 TableModel modelPopup = new DefaultTableModel(dataPopup, columnNamesPopup)
						    	  {
						    	    public boolean isCellEditable(int row, int column)
						    	    {
						    	      return false;//This causes all cells to be not editable
						    	    }
						    	  };
						    	  JTable tablePopup = new JTable(modelPopup);
						    	
								//JTable table = new JTable(data, columnNames);
						    	 tablePopup.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						    	 tablePopup.setFont(new Font("Verdana", Font.PLAIN, 14));
								
								for(int i = 0; i < dataPopup.length;i++) 
								{
									tablePopup.setRowHeight(i, 40);
								}
								
								JScrollPane pane1 = new JScrollPane(tablePopup);
								pane1.setBounds(50,200,600,200);
								
								framePopup.add(pane1);
								framePopup.add(titleLabel);
								framePopup.add(locationLabel);
								framePopup.add(dateLabel);
								framePopup.add(timeLabel);
								framePopup.add(deleteButton);
								framePopup.add(acceptButton);
								
								titleText.setText((String) data[row][1]);
								locationText.setText((String) data[row] [4]);
								dateText.setText((String) data[row][2]);
								timeText.setText((String) data[row][2]);
								
								framePopup.add(titleText);
								framePopup.add(locationText);
								framePopup.add(dateText);
								framePopup.add(timeText);
								
								framePopup.setSize(800,800);  
								framePopup.setLayout(null);  
								framePopup.setVisible(true);
								
								
								deleteButton.addActionListener(new ActionListener(){  
									public void actionPerformed(ActionEvent e){
										// search for events
										DataLayer.getInstance().SetEventStatus(eventList.get(row), false);
										
										}
									});
								
								acceptButton.addActionListener(new ActionListener(){  
									public void actionPerformed(ActionEvent e){
										// search for events
										DataLayer.getInstance().SetEventStatus(eventList.get(row), true);
										
										}
									});
					        	
					        	
					        }
					    }
					});
			        
			    }
				
	        	}  
			});
		     
		return frame1;
	}

}
