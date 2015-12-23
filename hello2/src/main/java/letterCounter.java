import java.util.Scanner;

public class letterCounter {
	public static void main (String[] args) {

		System.out.println("Finds the number of times a given letter appears in text.");

		Scanner user_input = new Scanner(System.in);

		System.out.print("Type or copy text here: ");
		String text = user_input.next();

		char letter = charScanner(user_input, "Type character to find here: ");

		user_input.close(); // We are done with the scanner now.

		findChar(text, letter);
	}
    public static char charScanner(Scanner scanner, String prompt) {
		char c = '$'; //value will be parsed again
		System.out.print(prompt);
		boolean errorAlert = false;
		while(!errorAlert) {
			errorAlert = true;
			String s = scanner.next();
			if (s.length() > 1) {
				System.out.println("Please input one character only!");
				errorAlert = false;
			} else {
				c = s.charAt(0); //get character from String
			}
		}
		return c;
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