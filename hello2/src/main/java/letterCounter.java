import java.util.Scanner;

import static tools.getScanner.getChar;

public class letterCounter {
	public static void main (String[] args) {

		System.out.println("Finds the number of times a given letter appears in text.");

		Scanner user_input = new Scanner(System.in);

		System.out.print("Type or copy text here: ");
		String text = user_input.next();

		char letter = getChar(user_input, "Type character to find here: ");

		user_input.close(); // We are done with the scanner now.

		findChar(text, letter);
	}

	//findChar taken from textbook
	public static void findChar (String s, char c) {
		int length = s.length();
		int count = 0;
		int index = 0;

		while (index < length) {
			if(s.charAt(index) == c) {
				count = count + 1;
			}
			index = index + 1;
		}
		System.out.println("The character " + c + " appears " + index + "times.");
	}
}