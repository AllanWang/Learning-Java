package comp202;

public class Lecture9 {
 //2016/01/25
	public static void main (String[] args) {
		if (args.length == 1) {
			isNegative(Integer.parseInt(args[0]));
		}
		
		if (args.length == 2) {
			printDivisible(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		}
		
	}
	
	public static void isNegative(int number) { //checks if given int is a number or not
		if (number < 0) {
			System.out.println("The number " + number + " is negative.");
		} else {
			System.out.println("The number " + number + " is not negative.");
		}
	}
	
	public static boolean isDivisible (int n1, int n2) { //returns true if n1 is evenly divisible by n2
		if (n1 % n2 == 0) {
			return true;
		}
//		} else {
			return false; 
			//not necessary for this to be under "else", 
			//as if the "if" is true, true is returned and the rest of the code is not read
//		}
	}
	
	public static void printDivisible (int n1, int n2) { //uses isDivisible, but prints text rather than returning a boolean
		if(isDivisible(n1, n2)) {
			System.out.println(n1 + " is evenly divisible by " + n2 + ".");
		} else {
			System.out.println(n1 + " is not evenly divisible by " + n2 + ".");
		}
	}
}
