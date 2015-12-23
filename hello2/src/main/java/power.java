import java.util.Scanner;

class power {
    public static void main (String[] args) {
        
        System.out.println("This will calculate the value for x^n");
        
        Scanner user_input = new Scanner(System.in);
               
        Double x = getDouble(user_input, "x : ");
        int n = getInt(user_input, "n : ");
                           
        user_input.close(); // We are done with the scanner now.
           
        Double m = x;
        power(x, n, m);
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
        }
        return input;
    }
    public static void power (Double x, int n, Double m) {
        String pV = "The value of x^n is ";
        if (n == 0 && x != 0) {
            System.out.println(pV + "1");
        } else if (n == 0 && x == 0) {
            System.out.println("0^0 is undefined");
        } else if (n == 1) {
            System.out.println(pV + m);
        } else if (n > 1) {
            n = n - 1;
            m = m * x;
            power(x, n, m);
        } else {
            x = 1/x;
            m = x;
            n = -n;
            power(x, n, m);
        }
        
    }
}