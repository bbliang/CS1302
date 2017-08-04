package edu.uga.cs1302.txtbuff;

/**
 * This class contains constructors and methods to represent a line of text as an array of characters
 * and to display its attributes and qualities.
 * 
 * @author Bryan Liang
 *
 */
public class TextLine {
	
	/**
	 * This is a constant that represents the generic size of the character array.
	 * It is public for global, easy access and final to prevent mutation of the constant.
	 */
	public final int DEFAULT_SIZE = 80;
	
	protected int capacity = 0; //protected to be access by EditableTextLine
	protected char[] charArray = new char[0]; //protected to be access by EditableTextLine
	protected int length = 0; //protected to be access by EditableTextLine
	
	/**
	 * Constructs a new TextLine object with an empty character array. 
	 */
	public TextLine() {
		length = 0;
		capacity = DEFAULT_SIZE;
		charArray = new char[capacity];
		
	}
	
	/**
	 * Constructs a new TextLine object with an character array containing the text
	 * from the parameter.
	 * @param line the line of text to be represented by the TextLine object 
	 */
	public TextLine(String line) {
		length = line.length();
		while (capacity < length)
			capacity += DEFAULT_SIZE;
		
		charArray = new char[capacity];
		for (int i = 0; i < line.length(); i++)
			charArray[i] = line.charAt(i);
		
		
	}
	
	/**
	 * Locates the first occurrence of the fragment in the TextLine.
	 * @param fragment the text to be searched for within TextLine
	 * @return index of the fragment's first location or -1 if not found
	 */
	public int indexOf(String fragment) {
		int index = -1;
		
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == fragment.charAt(0)) {
				boolean isFound = true;
				for (int j = 0; j < fragment.length() && i + j < charArray.length; j++) {
					if (charArray[i + j] != fragment.charAt(j)) {
						isFound = false;
						break;
					}
				}
				if (isFound) {
					index = i;
					break;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * Locates the fragment in the line beginning at the specified index
	 * @param fragment the text to be searched for within the line
	 * @param fromIndex the specific index at which to begin the search
	 * @return index of the fragment's first location after fromIndex or -1 if not found
	 */
	public int indexOf(String fragment, int fromIndex) {
		int index = -1;
		
		for (int i = fromIndex; i < charArray.length; i++) {
			if (charArray[i] == fragment.charAt(0)) {
				boolean isFound = true;
				for (int j = 0; j < fragment.length() && i + j < charArray.length; j++) {
					if (charArray[i + j] != fragment.charAt(j)) {
						isFound = false;
						break;
					}
				}
				if (isFound) {
					index = i;
					break;
				}
			}
		}
		
		return index;
	}
	
	/**
	 * Accessor method for the length of text in the TextLine.
	 * @return value of length
	 */
	public int length() {
		return length;
	}
	
	/**
	 * Accessor method for the size of the character array. 
	 * @return value of capacity
	 */
	public int capacity() {
		return capacity;
	}
	
	/**
	 * Compares the character arrays between two objects
	 * @return whether the two objects are equivalent
	 */
	@Override
	public boolean equals(Object anObject) {
		if (anObject instanceof TextLine) {
			for (int i = 0; i < charArray.length; i++) {
				if (charArray[i] != ((TextLine)anObject).charArray[i])
					return false;
			}
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Provides a string representation of the values stored within the TextLine
	 * character array.
	 * @return the String representation of charArray
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < charArray.length; i++)
			s += charArray[i];
		return s;
	}
}
