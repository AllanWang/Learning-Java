import java.util.Scanner;
import java.text.DecimalFormat;

import static tools.getScanner.getDouble;

public class quad {
        public static void main (String[] args) {
               
                Scanner user_input = new Scanner(System.in);
               
                Double a = getDouble(user_input, "Coefficient of x^2: ");
                Double b = getDouble(user_input, "Coefficient of x: ");
                Double c = getDouble(user_input, "Constant: ");
                               
                user_input.close(); // We are done with the scanner now.
               
                Double x1 = (-b + Math.sqrt(b*b-4*a*c))/(2*a);
                Double x2 = (-b - Math.sqrt(b*b-4*a*c))/(2*a);
                               
                DecimalFormat fmt = new DecimalFormat("+ #.#####;- #.#####");
               
                if (Double.isNaN(x1)) {
                        original(a, b, c);
                        System.out.println(" does not cross the x axis");
                        System.out.println("There are no real values for x");
                } else if (x1.equals(x2)) {
                        original(a, b, c);
                        System.out.println(" = (x " + fmt.format(-x1) + ")^2");
                        System.out.println("X is " + x1);
                } else {
                        original(a, b, c);
                        System.out.println(" = (x " + fmt.format(-x1) + ")(x " + fmt.format(-x2) + ")");
                        System.out.println("X can be " + x1 + " or " + x2);
                }
        }
       
        public static void original (double a, double b, double c) {
                System.out.print(a + "x^2 + " + b + "x + " + c);
        }
}