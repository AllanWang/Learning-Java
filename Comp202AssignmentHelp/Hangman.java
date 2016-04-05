//Allan Wang	260667681

import java.util.Random;
import java.util.Scanner;

/*
 * This is a hangman game
 * It will take an input of a word/phrase and a number for guesses,
 * then recreate the word/phrase using the letter class and allow you to guess it
 */
public class Hangman {
	private Letter[] letters; //will store the word itself
	private char[] guesses; //will store the current guesses (letters inputed that aren't in the word)
	private int[] alphabet = new int[26]; //will be used to save all previously inputed letters
	private boolean gameOver = false; //in this case, gameOver means that the game is over, not that you have lost
	private boolean win = false;
	private boolean space = false; //is there a space/is it a phrase
	private static final String[] words = {"comp two zero two", //some words in case there is no input
			"guess me", 
			"java", 
			"hippopotamus", 
			"z is for zebra", 
			"yttrium", 
			"can you guess this",
			"eclipse",
			"dimethyl sulfoxide"};
	private int maxNumGuesses;
	private int numGuessesMade = 0;
	private static final int defaultNumGuess = 8;
	private static Scanner user_input = new Scanner(System.in);
	
	//new Hangman with default number of guesses
	public Hangman(String s) {
		s = s.toUpperCase();
		letters = new Letter[s.length()];
		for (int i = 0; i < s.length(); i++) {
			letters[i] = new Letter(s.charAt(i));
			if (s.charAt(i) == ' ') { //character is a space, reveal it
				letters[i].reveal();
				space = true;
			}
		}
		maxNumGuesses = defaultNumGuess;
		guesses = new char[maxNumGuesses];
	}
	
	//new Hangman with given number of guesses
	public Hangman(String s, int max) {
		s = s.toUpperCase();
		letters = new Letter[s.length()];
		for (int i = 0; i < s.length(); i++) {
			letters[i] = new Letter(s.charAt(i));
			if (s.charAt(i) == ' ') { //character is a space, reveal it
				letters[i].reveal();
				space = true;
			}
		}
		maxNumGuesses = max;
		if (maxNumGuesses > 26) {
			System.out.println("There are only 26 letters in the alphabet; you don't need that many guesses!");
			maxNumGuesses = 26;
		}
		guesses = new char[maxNumGuesses];
	}
	
	//the main method; initializes Hangman
	public static void main (String[] args) {
		welcome();
		String word = getString("Input a word or phrase:\nLeave blank for a random one");
		if (word.replaceAll("\\s+","").length() == 0) { //word is empty
			System.out.println("No input, using a random one.");
			word = (words[new Random().nextInt(words.length)]);
		}
		int guesses = getInt("\nHow many guesses would you like?\nDefault is " + defaultNumGuess);

		System.out.println("\n\n\n\n\n"); //in case the word was a user input, add some spacing to hide it
		
		Hangman hangman = new Hangman(word, guesses);
		hangman.playGame();
	}
	
	//plays the game
	public void playGame() {				
		while(!gameOver) { //game is not over; keep playing
			char c = '\u8909'; //will be changed
			while (!guess(c)) { //guess matched at least one letter; check if you have won
				displayBoard(); //display current stats
				c = getChar("\nWhat is your next guess?");
				divider(); //mainly to separate the tries so they are more visible
			}	
			didYouWin();
		}
				
		results(win);
		user_input.close(); //game is over; we're done with the scanner
	}
	
	private static int getInt (String prompt) {
        boolean errorAlert = true;
        while(errorAlert) {
            errorAlert = false;
            try {
                System.out.println(prompt);
                String s = user_input.nextLine();
                if (s.replaceAll("\\s+","").length() == 0) { //integer input is empty
                	System.out.println(defaultNumGuess);
                	return defaultNumGuess;
                }
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Input must be an integer.");
                errorAlert = true;
            }
        }
        return defaultNumGuess;
    }
	
	//the following are a few scanner methods for converting the input to the desired variable
	
	private static String getString (String prompt) {
		String word = new String();
		boolean errorAlert = true;
		while(errorAlert) {
			errorAlert = false;
			System.out.println(prompt);
			word = user_input.nextLine();
			String s = word.toLowerCase();
			alphabetLoop:
			for(int i = 0; i < s.length(); i++) {
				if (!isCharValid(s.charAt(i)) && s.charAt(i) != ' ') { //char is not a letter or space
					errorAlert = true;
					System.out.println("Make sure the input contains only letters or spaces!");
					break alphabetLoop;
				}
			}
		}
		return word;
	}
	
	private static char getChar(String prompt) {
        char c = '$'; //value will be parsed again
        boolean errorAlert = true;
        while(errorAlert) {
        	errorAlert = false;
            System.out.println(prompt);
            String s = new String();
            s = user_input.next();
            if (s.length() > 1) {
                System.out.println("Please input one character only!");
                errorAlert = true;
            } else {
                c = s.charAt(0); //get character from String
            }
        }
        return c;
    }
	
	//is the character a letter
	private static boolean isCharValid(char c) {
		if (Character.toLowerCase(c) >= 'a' && Character.toLowerCase(c) <= 'z') {
			return true;
		}
		return false;
	}
	
	/* converts a character to an integer; used for getting item position in the alphabet array 
	 * a = 0, b = 1.... z = 25
	 */
	private static int char2int(char c) {
		int i = Character.toUpperCase(c) - 'A'; //c should always be uppercase, but this is just to be sure
		return i;
	}
	
	/* method for when a guess is made
	 * it will store the guessed letter into the alphabet array
	 * and check if it is in the word
	 * if not, it will store it into the guess array and add 1 to the number of guesses made
	 * if the number of guesses made reaches it's max, it will end the game
	 */
	private boolean guess(char c) {
		if (c == '\u8909') { //initial value; this shouldn't be an error
			return false;
		}
		c = Character.toUpperCase(c);
		if (!isCharValid(c)) {
			System.out.println("Please input a letter!");
			return false;
		}
		boolean matchFound = false;
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].getValue() == c) {
				letters[i].reveal();
				matchFound = true; //there is a match
			}
		}
		if (alphabet[char2int(c)] == 1) { //letter has already been guessed before
			System.out.println("\nYou've already guessed this letter. Try again.");
			if (matchFound) { //it's a correct letter; I think this should technically be an invalid guess
				return true;  //but the instructions say otherwise
			}
			return false;
		}
		alphabet[char2int(c)] = 1;
		if (!matchFound) {
			guesses[numGuessesMade] = c;
			numGuessesMade++;
			if (numGuessesMade == maxNumGuesses) {
				gameOver = true;
			}
		}
		return true;
	}
	
	//checks if all the letters are revealed; if they are, you win
	private void didYouWin() {
		for (int i = 0; i < letters.length; i++) {
			if (!letters[i].getRevealed()) {
				return;
			}
		}
		gameOver = true;
		win = true;
	}
	
	private void displayBoard() {
		String word = new String();
		String sGuesses = new String();
		int rGuesses;
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].getRevealed()) {
				word = word + " " + letters[i].getValue();
			} else {
				word = word + " _";
			}
		}
		guessLoop:
		for (int i = 0; i < guesses.length; i++) {
			if (guesses[i] != '\0') {
				sGuesses = sGuesses + guesses[i] + ", "; //saves the guesses as a string
			} else {
				break guessLoop; //guess item is empty; no more guesses; stop
			}
		}
		
		//the actual displaying in this method
		if (!space) {
			System.out.println("\nThe word:" + word);
		} else {
			System.out.println("\nThe phrase:" + word);
		}
		if (sGuesses.length() < 3) {
			System.out.print("\nNo guesses made yet");
		} else {
			System.out.print("\nGuesses: " + sGuesses.substring(0, sGuesses.length() - 2)); //remove last comma and space
		}
		rGuesses = maxNumGuesses - numGuessesMade;
		if (rGuesses > 1) {
			System.out.println("\t\tRemaining guessses: " + rGuesses);
		} else if (rGuesses == 1) {
			System.out.println("\t\t1 remaining guess!");
		} else {
			System.out.println("\t\tNo more guesses...");
			gameOver = true;
		}
	}
	
	//below are images that are used in the methods above
	
	private static void divider() {
		System.out.println("\n-----------------------------------------------\n");
//		System.out.println("        |   \\|");
//		System.out.println("       (_)   |");
//		System.out.println("       -|-   |");
//		System.out.println("       / \\   |");
//		System.out.println("_____________|__\n");
	}
	
	private static void welcome() {
		System.out.println("                ______");
		System.out.println("   Welcome      |   \\|");
		System.out.println("     to        (_)   |");
		System.out.println("   Hangman!    -|-   |");
		System.out.println("               / \\   |");
		System.out.println("_____________________|__\n");
	}
	
	public void results(boolean win) {
		if (win) {
			System.out.println("\n                     ______");
			System.out.println("                     _   \\|");
			System.out.println("  Congratulations,  (_)   |");
			System.out.println("      You Win!      \\|/   |");
			System.out.println("                     |    |");
			System.out.println("____________________/_\\___|__");
		} else {
			System.out.println("\n                ______");
			System.out.println("                |   \\|");
			System.out.println("   Game Over   (_)   |");
			System.out.println("   You Lose.   /|\\   |");
			System.out.println("               / \\   |");
			System.out.println("______________     __|__");
			
		}
		
		String s = new String();
		
		//get the word and print it
		for(int i = 0; i < letters.length; i++) {
			s = s + letters[i].getValue();
		}
		
		if (!space) {
			System.out.println("\nThe word was " + s);
		} else {
			System.out.println("\nThe phrase was " + s);
		}
	}
	
}
