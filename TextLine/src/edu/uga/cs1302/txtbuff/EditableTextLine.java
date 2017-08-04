package edu.uga.cs1302.txtbuff;

/**
 * Implements the methods provided by Editable and retains the attributes
 * and actions of its superclass, TextLine.
 * @author Bryan Liang
 *
 */
public class EditableTextLine extends TextLine implements Editable{

	/**
	 * Constructs a new TextLine object with an empty character array with the parent constructor.
	 */
	public EditableTextLine() {
		super();
	}
	
	/**
	 * Constructs a new TextLine object with an character array containing the text
	 * from the parameter with the parent constructor.
	 * @param line the line of text to be represented by the TextLine object 
	 */
	public EditableTextLine(String line) {
		super(line);
	}
	
	public void append(String fragment) {
		length += fragment.length();
		while (capacity < length)
			capacity += DEFAULT_SIZE;
		
		char[] temp = new char[capacity];
		for (int i = 0; i < length - fragment.length(); i++)
			temp[i] = charArray[i];
		for (int i = length - fragment.length(), j = 0; i < length; i++, j++)
			temp[i] = fragment.charAt(j);
		charArray = temp;
	}
	
	public void insert(int index, String fragment) throws TextLineIndexOutOfBoundsException {
		if (index >= capacity || index < 0)
			throw new TextLineIndexOutOfBoundsException(index);
		int gap = 0;
		boolean greater = index > length;
		if (greater)
			 gap = index - length;
		length = length + fragment.length() + gap;
		while (capacity < length)
			capacity += DEFAULT_SIZE;
		
		char[] temp = new char[capacity];
		for (int i = 0; i < index; i++)
			temp[i] = charArray[i];
		for (int i = index, j = 0; i < index + fragment.length(); i++, j++)
			temp[i] = fragment.charAt(j);
		if (!greater)
			for (int i = index + fragment.length(), j = index; i < length; i++, j++)
				temp[i] = charArray[j];
		
		charArray = temp;
	}
	
	public void replace(int start, int end, String fragment) throws TextLineIndexOutOfBoundsException {
		
		if (start < 0 || start >= capacity || start > end)
			throw new TextLineIndexOutOfBoundsException(start);
		if (end < 0 || end > capacity)
			throw new TextLineIndexOutOfBoundsException(end);
		
		length = fragment.length() + start + (capacity - end);
		while (capacity < length)
			capacity += DEFAULT_SIZE;
		
		char[] temp = new char[capacity];
		for (int i = 0; i < start; i++)
			temp[i] = charArray[i];
		for (int i = start, j = 0; i < start + fragment.length(); i++, j++)
			temp[i] = fragment.charAt(j);
		for (int i = start + fragment.length(), j = end; i < length; i++, j++)
			temp[i] = charArray[j];
		
		charArray = temp;
		for (int i = capacity - 1; i >= 0; i--) {
			if (charArray[i] != '\u0000') {
				length = i + 1;
				break;
			}
		}
		
	}
}
