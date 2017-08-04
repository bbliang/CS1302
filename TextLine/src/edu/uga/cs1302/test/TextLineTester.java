package edu.uga.cs1302.test;

import edu.uga.cs1302.txtbuff.*;
import java.util.Scanner;

/**
 * Application to create TextLine objects, display its properties, and manipulate the objects.
 * @author Bryan Liang
 *
 */
public class TextLineTester {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		TextLine tLine = new TextLine("one line of text");
		
		System.out.println("Enter a line of text: ");
		EditableTextLine eLine = new EditableTextLine(keyboard.nextLine());
		
		System.out.println("eLine and tLine are: " + (eLine.equals(tLine)?"Equal":"Different"));
		System.out.println(eLine.toString());
		System.out.println("Length of eLine: " + eLine.length());
		System.out.println("Capacity of eLine: " + eLine.capacity());
		System.out.println();
		
		System.out.println("Enter a string of characters: ");
		String str = keyboard.nextLine();
		
		System.out.println("All index positions of str in eLine: ");
		EditableTextLine temp = new EditableTextLine(eLine.toString());
		while (temp.indexOf(str) != -1) { //why is temp length at 20
			System.out.println(temp.indexOf(str));
			temp.replace(temp.indexOf(str), temp.indexOf(str) + 1, "~");
		}
		
		eLine.append(str);
		System.out.println("Append str to eLine: " + eLine.toString());
		eLine.insert(0, str);
		System.out.println("Insert str in eLine at index 0: " + eLine.toString());
		eLine.insert(str.length(), str);
		System.out.println("Insert str in eLine at index str.length(): " + eLine.toString());
		
		while (eLine.indexOf(str) != -1)
			eLine.replace(eLine.indexOf(str), eLine.indexOf(str) + str.length(), "abc");
		System.out.println("Replace all occurrences of str with 'abc': " + eLine.toString());
	}
}
