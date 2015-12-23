import java.util.Scanner;
 
class isDivisible {
        public static void main (String[] args) {
               
                Scanner user_input = new Scanner(System.in);
               
                Double n = getDouble(user_input, "Dividend: ");
                Double m = getDouble(user_input, "Divisor: ");
                               
                user_input.close(); // We are done with the scanner now.
               
                System.out.println(isDivislble(n, m));
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
       
        public static String isDivislble (double n, double m) {
                if (n % m == 0) {
                    String dYes = n + " is divisible by " + m;
                    return dYes;
                } else {
                    String dNo = n + " is not divisible by " + m;
                    return dNo;
                }
        }
}