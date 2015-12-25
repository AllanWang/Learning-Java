package projects;

import java.util.Scanner;
import static tools.Time.getTime;
import static tools.Time.printTime;
import static tools.Time.addTime;

import tools.Time;

public class TimeAddition {
	public static void main(String[] args) {
		System.out.println("The following program will print out a new time after a given period passes from a given time.");
		
		Scanner user_input = new Scanner(System.in);
		
		Time t1 = new Time();
		Time t2 = new Time();
		getTime(t1, user_input, "Input original time: ");
		getTime(t2, user_input, "Input time to add: ");
		user_input.close(); // We are done with the scanner now.
				
		printTime(addTime(t1, t2));
	}
	
}
