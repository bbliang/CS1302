package edu.uga.cs1302.txtbuff;

/**
 * This class contains the unique and specific exceptions useful for the EditableTextLine class.
 * @author Bryan Liang
 *
 */
public class TextLineIndexOutOfBoundsException extends java.lang.IndexOutOfBoundsException{
	
	/**
     * Default constructor for TextLineIndexOutOfBoundsException which calls on the default constructor of the
     * IndexOutOfBoundsException when thrown.
     */
	public TextLineIndexOutOfBoundsException() { //default constructor
		super();
	}
	
	/**
     * Constructor for TextLineIndexOutOfBoundsException which calls on the constructor of the
     * IndexOutOfBoundsException when thrown.
     * @param errMsg error message to be printed
     */
	public TextLineIndexOutOfBoundsException(String errMsg) {
		super(errMsg);
	}
	
	/**
     * Constructor for TextLineIndexOutOfBoundsException which calls on the constructor of the
     * IndexOutOfBoundsException when thrown.
     * @param index the index at which the error occurred at
     */
	public TextLineIndexOutOfBoundsException(int index) {
		super("TextLine index out of range: " + index);
	}
}
