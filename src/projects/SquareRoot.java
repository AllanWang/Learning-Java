package projects;

import java.util.Scanner;

import tools.GetScanner;

public class SquareRoot {
    public static void main(String[] args){
    
        System.out.println("This will find the square root of the given number");
        
        Scanner user_input = new Scanner(System.in);
               
        double orig = GetScanner.getDouble(user_input, "Input: ");
		
        user_input.close(); // We are done with the scanner now.
        System.out.print("The square root of " + orig + " is: " + squareRoot(orig) + ".");
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