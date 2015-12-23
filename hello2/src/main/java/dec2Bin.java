import java.util.Scanner;

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
       
    public static int getInt (Scanner scanner, String prompt) {
        int input = 777; //value will be parsed again
        boolean errorAlert = false;
        while(!errorAlert) {
            errorAlert = true;
            try {
                    System.out.print(prompt);
                    input = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                    System.out.println("Input must be an integer.");
                    errorAlert = false;
            }
            if (input < 0) {
                System.out.println("Input must be positive");
                errorAlert = false;
            }
        }
        return input;
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