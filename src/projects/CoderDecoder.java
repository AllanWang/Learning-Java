package projects;

import java.util.Scanner;

import tools.GetScanner;

//	2015/12/23

public class CoderDecoder {
	public static void main (String[] args) {
		System.out.println("The following program will shift the characters in your text to match a new alphabet");
		
		Scanner user_input = new Scanner(System.in);
        
		boolean proceed = false;
		String s = new String();
		int shift = 0;
		
		
		while (!proceed) {
			proceed = true;
			System.out.print("Input text: ");
		
			s = user_input.nextLine().replaceAll("\\s+",""); //used nextLine instead of next as inputting text may imply an entire sentence, spaces are automatically removed
	                           
	        if (!isInputValid(s.toLowerCase())) {
	        	System.out.println("Make sure that your text only contains letters! Punctuations and symbols are not allowed. Spaces will be automatically removed");
				proceed = false;
			}
		}
		
		shift = GetScanner.getInt(user_input, "Select value for character shift: ");
		user_input.close(); // We are done with the scanner now.
			
        coderDecoder(s, shift);
	}
	
	//makes sure that there are only letters in the string
	public static boolean isInputValid(String s) {
		int length = s.length();
		boolean proceed = true;
		while (length > 0) {
			if (s.charAt(0) >= 'a' && s.charAt(0) <= 'z') {
				s = s.substring(1, s.length());
				length--;
			} else {
				proceed = false;
				break;
			}
		}
		return proceed;
	}
	
	//creates character shifts
	public static void coderDecoder(String s, int shift) {
		String coded = new String();
		int index = s.length();
		while (index > 0) {
			int charShift = shift; 
			char c = s.charAt(0);
			if (charShift > 0) {
				while (charShift > 0) {
					c = charShiftUp(c);
					charShift--;
				}
			} else if (charShift < 0) {
				while (charShift < 0) {
					c = charShiftDown(c);
					charShift++;
				}
			}

			coded = coded + c;
			s = s.substring(1, s.length());
			index--;
		}
		System.out.println("New message: " + coded);
	}
	
	//charShift implementations ensure that the shifts occur within the alphabet
	public static char charShiftUp(char c) {
		if (c == 'z') {
			c = 'a';
		} else if (c == 'Z') {
			c = 'A';
		} else {
			c++;
		}
		return c;
	}
	
	public static char charShiftDown(char c) {
		if (c == 'a') {
			c = 'z';
		} else if (c == 'A') {
			c = 'Z';
		} else {
			c--;
		}
		return c;
	}
}
