package edu.uga.cs1302.txtbuff;

/**
 * Contains methods to manipulate the TextLine.
 * @author Bryan Liang
 *
 */
public interface Editable {

	/**
	 * Appends the given text to the end of the TextLine.
	 * @param fragment the string to append
	 */
	public void append(String fragment);
	
	/**
	 * Inserts a portion of text at the specified index in the TextLine.
	 * @param index the specific location to insert the fragment
	 * @param fragment the string of text to insert
	 * @throws TextLineIndexOutOfBoundsException when the supplied index is out of bounds of the TextLine
	 */
	public void insert(int index, String fragment) throws TextLineIndexOutOfBoundsException;
	
	/**
	 * Removes a section of text between the two indices and inserts a portion of text in TextLine.
	 * @param start the lower index for replacement
	 * @param end the upper index for replacement
	 * @param fragment the portion of text to be inserted between the upper and lower indices
	 * @throws TextLineIndexOutOfBoundsException when one the supplied indices are out of bounds of the TextLine
	 */
	public void replace(int start, int end, String fragment) throws TextLineIndexOutOfBoundsException;
}
