package edu.uga.cs1302.mytunes;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import edu.uga.cs1302.mytunes.MP3File;

/**
 * Creates a collection of MP3File elements from a given path with play and pause functionalities
 * @author Bryan Liang
 *
 */
public class MP3Collection {

	private File dir;
	private List<MP3File> mp3 = new LinkedList<MP3File>();
	private MP3Player player = new MP3Player();
	
	/**
	 * Default constructor that creates an empty linked list of MP3File elements
	 */
	public MP3Collection() {
		mp3 = new LinkedList<MP3File>();
	}
	
	/**
	 * Creates a linked list of MP3File elements from the given path
	 * @param directoryPathname the given path
	 */
	public MP3Collection(String directoryPathname) {
		//Object declaration
		File dir = new File(directoryPathname);
		mp3 = new LinkedList<MP3File>();
		
		//add mp3file objects to directory
		File[] paths;
		try {
			paths = dir.listFiles(); //get array of File objects
			for (File path:paths) {
				if (path.getAbsolutePath().contains(".mp3")) //check if file is mp3
					mp3.add(new MP3File(path.getAbsolutePath())); //add new MP3File objects
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * Creates a table of MP3File properties
	 * @return an array of MP3File properties
	 */
	public Object[][] getTableData() {
		Object[][] newArray = new String[size()][4];
		
		ListIterator<MP3File> iter = mp3.listIterator();
		for (int row = 0; row < newArray.length; row++) {
			newArray[row][0] = iter.next().getAuthor();
			iter.previous();
			
			newArray[row][1] = iter.next().getTitle();
			iter.previous();
			
			newArray[row][2] = iter.next().getAlbum();
			iter.previous();
			
			newArray[row][3] = Integer.toString(iter.next().getYear());
			iter.previous();
			
			iter.next();
		}
		
		return newArray;
	}
	
	/**
	 * Retrieves the number of elements in the MP3File collection
	 * @return the size of the list
	 */
	public int size() {
		return mp3.size();
	}
	
	/**
	 * Accessor method for the MP3File at a given index
	 * @param index the location of the MP3File in the collection
	 * @return the MP3File at the given index
	 * @throws IndexOutOfBoundsException if the index is past the size of the list
	 */
	public MP3File getFile(int index) throws IndexOutOfBoundsException {
		ListIterator<MP3File> iter = mp3.listIterator(index);
		return iter.next();
	}
	
	/**
	 * Begins playing the MP3File at the given index
	 * @param index the location of the MP3File in the collection 
	 * @throws IndexOutOfBoundsException if the index is beyond the size of the list
	 */
	public void startPlay(int index) throws IndexOutOfBoundsException {
		player.play(getFile(index).getPath());
	}
	
	/**
	 * Terminates the playing of an MP3File
	 */
	public void stopPlay() {
		player.stop();
	}

}
