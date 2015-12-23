package projects;

import static tools.getScanner.getInt;

import java.util.Scanner;

//	2015/12/23

public class Abecedarian {
	public static void main (String[] args) {
		System.out.println("The following program tests to see if a word is abecedarian, ");
		System.out.println("or if the characters in the letters are in alphabetical order");

		Scanner user_input = new Scanner(System.in);
        
		System.out.print("Input word: ");
		String s = user_input.next();
                           
        user_input.close(); // We are done with the scanner now.
        
        isAbecedarian(s);
	}
	
	public static void isAbecedarian(String s) {
		int length = s.length();
		String orig = s; //original string kept for display
		boolean isAbe = true; 
		while (length > 1) {
			if (s.charAt(0) <= s.charAt(1)) {
				s = s.substring(1, s.length()); //removes the first character so the other ones are compared
				length--;
			} else {
				System.out.println("\"" + orig + "\" is not abecedarian.");
				isAbe = false;
				break;
			}
		}
		if (isAbe) {
			System.out.println("\"" + orig + "\" is abecedarian!");
		}
	}
}
