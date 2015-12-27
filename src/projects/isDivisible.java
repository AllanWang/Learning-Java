package projects;

import java.util.Scanner;

import tools.GetScanner;

public class IsDivisible {
        public static void main (String[] args) {
               
                Scanner user_input = new Scanner(System.in);
               
                Double n = GetScanner.getDouble(user_input, "Dividend: ");
                Double m = GetScanner.getDouble(user_input, "Divisor: ");
                               
                user_input.close(); // We are done with the scanner now.
               
                System.out.println(isDivislble(n, m));
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