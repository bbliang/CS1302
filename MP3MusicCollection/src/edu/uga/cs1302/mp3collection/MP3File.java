package edu.uga.cs1302.mp3collection;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Scanner;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

/**
 * Describes ADT MP3File with music file related attributes and functionalities
 * @author Bryan Liang
 *
 */
public class MP3File {

	private String path = "";
	private String author = "";
	private String album = "";
	private String date = "";
	private String title = "";
	private MP3Player player = new MP3Player();
	
	/**
	 * Default constructor which gives all attributes blank String values
	 */
	public MP3File() {
		path = "";
		author = "";
		album = "";
		date = "";
		title = "";
		player = new MP3Player();
	}
	
	/**
	 * Locates the properties of the mp3 file and stores it into the attribute variables in the MP3 object
	 * @param pathname the path for the method to look into
	 */
	public MP3File(String pathname) {
		
		try {
		    FileInputStream fis        = new FileInputStream( pathname );
		    BufferedInputStream bis    = new BufferedInputStream(fis);
		    AudioFileFormat mpegFormat = AudioSystem.getAudioFileFormat(bis);
		    Map properties             = mpegFormat.properties();
		    
		    path = pathname;
		    author = (String) properties.get("author");
		    album = (String) properties.get("album");
		    title = (String) properties.get("title");
		    date = (String) properties.get("date");

		}
	    catch( ArrayIndexOutOfBoundsException oobe ) {
	    	System.err.println( "Usage: java PrintMP3Properties file.mp3" );
	    }
		catch( Exception e ) {
			System.out.println(e);
		}
	}
	
	/**
	 * Accessor method to return path name
	 * @return the name of the path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Mutator method for path
	 * @param pathname the path to set it to
	 */
	public void setPath(String pathname) {
		path = pathname;
	}
	
	/**
	 * Accessor method for the album variable
	 * @return the name of the album
	 */
	public String getAlbum() {
		return album;
	}
	/**
	 * Mutator method for the album variable
	 * @param album the name of the album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	
	/**
	 * Accessor method for the date attribute
	 * @return the date attribute of the MP3File
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Mutator method for the date attribute
	 * @param date the date String to set the attribute to
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Accessor method for the title of the MP3File
	 * @return the title of the MP3File
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Mutator method for the title attribute
	 * @param title String to set the title attribute to
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Provides the String representation of the MP3File object
	 * @return String of the MP3File attributes
	 */
	@Override
	public String toString() {
		return "Information about: " + path + "\n"
				+ "-------------------------- \n"
				+ "Author: " + author + "\n"
				+ "Album: " + album + "\n"
				+ "Title: " + title + "\n"
				+ "Date: " + date;
	}
	
	/**
	 * Compares the equality between two MP3File objects
	 * @param that the MP3File object to compare to
	 * @return whether the two MP3File objects are equal or not
	 */
	public boolean equals(MP3File that) {
		return (this.path == that.path
				&& this.author == that.author
				&& this.album == that.album
				&& this.title == that.title
				&& this.date == that.date);
	}
	
	/**
	 * Plays the MP3 File
	 */
	public void play() {
		Scanner   keyboard = null;

		boolean stop = false;
		String  selection = null;
		
		keyboard = new Scanner( System.in );

		// a simple illustration on how to play several songs
		while( !stop ) {

		    // start playing this file
		    player.play( path );
		    // but wait until it finishes, or is interrupted by the user
		    player.waitForPlaybackFinish();

		    // now, check what to do next
		    System.out.println( "RunMP3Player: Enter 'q' to quit the loop, or any other char to continue" );

		    selection = keyboard.next();
		    if( selection.equals( "q" ) )
			stop = true;
		}
	}
}

