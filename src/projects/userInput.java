package projects;

import java.util.Scanner;

public class UserInput {
	public static void main (String[] args) {
		
		Scanner user_input = new Scanner(System.in);
		
		String first_name;
		System.out.print("First name: ");
		first_name = user_input.next();
		
		String last_name;
		System.out.print("Last name: ");
		last_name = user_input.next();
		
		user_input.close(); // We are done with the scanner now.
		
		System.out.println("Hello " + first_name + " " + last_name);
	}
}