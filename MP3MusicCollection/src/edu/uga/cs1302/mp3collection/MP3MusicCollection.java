package edu.uga.cs1302.mp3collection;
import java.io.File;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Creates a SimpleArrayList of type MP3File, prints its contents, and executes an interactive menu
 * to explore the contents of the collection.
 * @author Bryan Liang
 */
public class MP3MusicCollection {

	/**
	 * Creates a SimpleArrayList of type MP3 File, prints its contents, and executes a program to explore
	 * the collection.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		//Object declaration
		Scanner keyboard = new Scanner(System.in);
		SimpleArrayList<MP3File> mp3 = new SimpleArrayList<MP3File>();
		
		//Prompt for directory
		System.out.println("Enter a valid directory name: ");
		File dir = new File(keyboard.nextLine());
		
		//add mp3file objects to directory
		File[] paths;
		try {
			paths = dir.listFiles(); //get array of File objects
			for (File path:paths)
				mp3.add(new MP3File(path.getAbsolutePath())); //add new MP3File objects with the constructor
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		//Print all entries
		ListIterator<MP3File> iter1 = mp3.listIterator(0);
		while (iter1.hasNext()) {
			System.out.println(iter1.next().toString());
			System.out.println();
		}
		System.out.println(iter1.next().toString()); //print last element
		System.out.println();
		
		//Menu
		ListIterator<MP3File> iter2 = mp3.listIterator(0);
		boolean on = true;
		while(on) {
			System.out.println("Select one of the following menu options:");
			System.out.println("n - move to the next file");
			System.out.println("b - move to the previous file");
			System.out.println("i - print the information about the current file");
			System.out.println("p - play the current file");
			System.out.println("h - read a short help information about commands");
			System.out.println("q - quit the program");
			System.out.println("-------------------------------------------------");
			switch(keyboard.nextLine()) {
				case "n": 
					if (iter2.hasNext())
						iter2.next();
					else
						System.out.println("end of the list");
					break;
				case "b":
					if (iter2.hasPrevious())
						iter2.previous();
					else
						System.out.println("top of the list");
					break;
				case "i":
					System.out.println(iter2.next().toString());
					iter2.previous();
					break;
				case "p":
					iter2.next().play();
					iter2.previous();
					break;
				case "h":
					System.out.println("Please read the instructions below for help.");
					break;
				default:
					on = false;
					break;
					
			}
			
		}
		
		
		
		
	}
}
