package edu.uga.cs1302.mytunes;

import java.awt.*;
import java.awt.event.*;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * Interface to sort, play, and search for MP3File elements in a collection
 * @author Bryan Liang
 *
 */
public class MyTunes extends JFrame {
    private JTable table;
    private JTextField txtFilter;
    private TableModel model;
    private TableRowSorter<TableModel> sorter;
    private MP3Collection mp3c;

    /**
     * Default constructor for MyTunes
     */
    public MyTunes() {
    	this("/Music/");
    }
    
    /**
     * Constructs the panel and components for this frame
     * @param pathname the location of the .mp3 files
     */
    public MyTunes (String pathname) {	
		super("MyTunes");
	
		//create a new instance of mp3collection
	    mp3c = new MP3Collection(pathname);
	    
		// setup the exit on window closing
		setDefaultCloseOperation( EXIT_ON_CLOSE );
	
		// set the starting size
	        setSize( 700, 400 );
	
		// set the minimum size (the window can't be made smaller)
	        setMinimumSize( new Dimension( 600, 400 ) );
	
		// create column names
		String columns[] = {"Author", "Title", "Album", "Year"};
	
		// get the rows array with JTable cell values;
		// the values in each row must correspond to the column labels
		Object[][] rows = mp3c.getTableData();
	
		//model = new DefaultTableModel( rows, columns );
	
	
		// create a TableModel with the rectangular data and column labels
		model = new DefaultTableModel( rows, columns ) {
			@Override
			public boolean isCellEditable(int row, int column) {
			    return false;
			}
		};
	
	
		// create a table attached to the created TableModel
		table = new JTable( model );
		//	table.setEnabled(false);
	
		// Attach a list selection listener to the table's selection model, to
		// be notified whenever the user selects a row. Use this opportunity to
		// output the view and model indices on the status label.
	
		/*
		ListSelectionListener lsl;
		lsl = new ListSelectionListener ()
	            {
	                public void valueChanged (ListSelectionEvent lse)
	                {
			    int index = table.getSelectedRow ();
			    if (index != -1)
				{
				    String status;
				    status = "View index = " + index + ", Model index = ";
				    status += table.convertRowIndexToModel (index);
				    lblStatus.setText (status);
				}
	                }
	            };
		table.getSelectionModel ().addListSelectionListener (lsl);
		*/
	
		// set up a TableRow Sorter for our table
		sorter = new TableRowSorter<TableModel>( model );
		table.setRowSorter( sorter );
	
		// create a scroll pane including the table
		JScrollPane scrollPane = new JScrollPane( table );
	
		// it's possible to set its preferred (and minimum/maximum) size
		scrollPane.setPreferredSize( new Dimension (300, 100) );
	
		// create a panel for buttons and filter text box
		JPanel buttonPanel = new JPanel();
		
		//create play and stopPlay buttons
		JButton play = new JButton("Play");
		play.addActionListener(new PlayButtonListener());
		buttonPanel.add(play);
		JButton stopPlay = new JButton("Stop");
		stopPlay.addActionListener(new StopButtonListener());
		buttonPanel.add(stopPlay);
		
		// create a label for the text box
		buttonPanel.add( new JLabel( "Filter:" ) );
	    buttonPanel.setMaximumSize( new Dimension( 1000, 350 ) );
	    buttonPanel.setMinimumSize( new Dimension( 800,  200 ) );
		
		// create a text box for the filter text
		txtFilter = new JTextField( 20 );
		txtFilter.addActionListener( new TextFieldListener() );
		buttonPanel.add( txtFilter );
		
		//create a button to cancel the filter
		JButton cancelFilter = new JButton("Cancel Filter");
		cancelFilter.addActionListener(new CancelButtonListener());
		buttonPanel.add(cancelFilter);
	
		// create an empty border around the frame
		Border border = BorderFactory.createEmptyBorder( 20, 20, 20, 20 );
		//Border border = BorderFactory.createEmptyBorder( 5, 5, 5, 5 );
		getRootPane().setBorder( border );
	
		// get the content pane of the frame
		Container c = getContentPane();
	
		// set its layout to BoxLayout along the Y-axis
	        c.setLayout( new BoxLayout( c, BoxLayout.Y_AXIS ) );
	        c.add( buttonPanel );
	        c.add( scrollPane );
	
		// display the frame and start processing events
		setVisible(true);
    }

    /**
     * Listener for when the play button is actuated
     * @author bryanliang
     *
     */
    private class PlayButtonListener implements ActionListener {
    	/**
    	 * Begins playing the MP3File when pressed
    	 */
    	public void actionPerformed(ActionEvent event) {
    	    int tableRow = table.getSelectedRow();
    	    
    	    if(tableRow == -1)
    	    	return;
    	    
    	    else {
    	    	mp3c.stopPlay();
    	    	mp3c.startPlay(tableRow);
    	    }
    	    
    	}
	}
   
    /**
     * Listener for when the stop button is pressed
     * @author bryanliang
     *
     */
    private class StopButtonListener implements ActionListener {
    	/**
    	 * Stops the track playing when pressed
    	 */
    	public void actionPerformed(ActionEvent event) {
    		mp3c.stopPlay();
    	}
    }
    
    /**
     * Listener for when text is entered
     * @author bryanliang
     *
     */
    private class TextFieldListener implements ActionListener {
		/**
		 * Searches the MP3File properties for the given part of text
		 */
    	public void actionPerformed( ActionEvent e ) {
			// Install a new row filter.
	
		    String expr = txtFilter.getText();
	
		    // create a row filter for our JTable's sorter
		    sorter.setRowFilter( RowFilter.regexFilter( expr ) );
	
		    // if you'd like a case insensitive filtering, add (?i) in front of the filter text
		    // sorter.setRowFilter( RowFilter.regexFilter( "(?i)" + expr ) );
	
		    // Unsort the view.
		    sorter.setSortKeys( null );
		}
    }
    
    /**
     * Listener for when the cancel button is pressed
     * @author bryanliang
     *
     */
    private class CancelButtonListener implements ActionListener {
    	/**
    	 * Resets the text field and displays all the MP3Files again
    	 */
    	public void actionPerformed(ActionEvent event) {
    		txtFilter.setText("");
    	    String expr = txtFilter.getText();

    	    // create a row filter for our JTable's sorter
    	    sorter.setRowFilter( RowFilter.regexFilter( expr ) );

    	    // if you'd like a case insensitive filtering, add (?i) in front of the filter text
    	    // sorter.setRowFilter( RowFilter.regexFilter( "(?i)" + expr ) );

    	    // Unsort the view.
    	    sorter.setSortKeys( null );
    	}
    }

    /**
     * Main method for starting the GUI
     * @param args input to specify the pathname
     */
    public static void main( String [] args ) {
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				Scanner keyboard = new Scanner(System.in);
				System.out.print("Enter a valid directory name: ");
				MyTunes tunesGUI = new MyTunes(keyboard.nextLine());
				tunesGUI.setVisible(true);
			}
		} );
    }
}
