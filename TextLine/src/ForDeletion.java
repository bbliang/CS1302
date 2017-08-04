import edu.uga.cs1302.txtbuff.*;
import java.util.Scanner;

public class ForDeletion {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		EditableTextLine e = new EditableTextLine(keyboard.nextLine());
		
		//System.out.println("APPEND TEST");
		//e.append("abc");
		//System.out.println(e.toString());
		
		System.out.println("INSERT TEST");
		e.insert(2, "a");
		System.out.println(e.toString());

	}

}
