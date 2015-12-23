package projects;

import java.util.Scanner;

import static tools.getScanner.getDouble;

public class triangle {
        public static void main (String[] args) {
               
                System.out.println("Tests to see if 3 given sides can form a triangle");
                
                Scanner user_input = new Scanner(System.in);
               
                Double a = getDouble(user_input, "Side 1: ");
                Double b = getDouble(user_input, "Side 2: ");
                Double c = getDouble(user_input, "Side 3: ");
                               
                user_input.close(); // We are done with the scanner now.
               
                System.out.println(canTriangle(a, b, c));
        }
       
        public static String canTriangle (double a, double b, double c) {
                if (a + b < c || a + c < b || b + c < c) {
                    String tNo = "A triangle cannot be formed";
                    return tNo;
                } else {
                    String tYes = "A triangle can be formed";
                    return tYes;
                }
        }
}