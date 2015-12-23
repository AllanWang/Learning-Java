import java.util.Scanner;

public class SquareRoot {
    public static void main(String[] args){
    
        System.out.println("This will find the square root of the given number");
        
        Scanner user_input = new Scanner(System.in);
               
        double orig = getDouble(user_input, "Input: ");
		
        user_input.close(); // We are done with the scanner now.
        System.out.print("The square root of " + orig + " is: " + squareRoot(orig) + ".");
    }
    public static double getDouble (Scanner scanner, String prompt) {
        double input = 777.7; //value will be parsed again
        boolean errorAlert = false;
        while(!errorAlert) {
            errorAlert = true;
            try {
                    System.out.print(prompt);
                    input = Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                    System.out.println("Input must be a number.");
                    errorAlert = false;
            }
        }
        return input;
    }
    public static double squareRoot (double orig) {
        double guess = orig/2;
        double calc = (guess + orig/guess)/2;
        while (Math.abs(guess-calc) >= 0.0001) {
            guess = calc;
            calc = (guess + orig/guess)/2;
        }
        return calc;
    }    
}