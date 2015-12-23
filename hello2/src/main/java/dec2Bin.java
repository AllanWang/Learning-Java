import java.util.Scanner;

import static tools.getScanner.getInt;

public class dec2Bin {
    public static void main (String[] args) {
        
        System.out.println("This will convert whole numbers from decimal to binary");
        
        Scanner user_input = new Scanner(System.in);
               
        int a = getInt(user_input, "Input number: ");
                           
        user_input.close(); // We are done with the scanner now.
        
        System.out.print(a + " is equal to the binary value of ");
        dec2Bin(a);
        System.out.println(".");
        
    }

    public static void dec2Bin (int a) {
        String x = new String();
        while (a > 0) {
            int b = a % 2;
            x = b + x;
            a = a/2;
        }
        System.out.println(x);
    }
}