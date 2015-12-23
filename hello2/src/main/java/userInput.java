import java.util.Scanner;

class userInput {
	public static void main (String[] args) {
		
		Scanner user_input = new Scanner(System.in);
		
		String first_name;
		System.out.print("First name: ");
		first_name = user_input.next();
		
		String last_name;
		System.out.print("Last name: ");
		last_name = user_input.next();
		
		System.out.println("Hello " + first_name + " " + last_name);
	}
}