import java.util.Scanner;

import static tools.getScanner.getInt;

public class gcd {
    public static void main (String[] args) {
        
        System.out.println("This will calculate the greatest common denominator for two integers.");
        
        Scanner user_input = new Scanner(System.in);
               
        int a = getInt(user_input, "First number: ");
        int b = getInt(user_input, "Second number: ");
                           
        user_input.close(); // We are done with the scanner now.
        
        System.out.print("The greatest common denominator of " + a + " and " + b + " is ");
        gcd(a, b);
        System.out.println(".");
    }

    public static void gcd (int a, int b) {
        if (a > b && b != 0) {
            a = a % b;
            gcd(a, b);
        } else if (a < b && a != 0) {
            b = b % a;
            gcd(a, b);
        } else if (b == 0) {
            System.out.print(a);
        } else {
            System.out.print(b);
        }
        
    }
}