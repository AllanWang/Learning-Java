import java.util.Scanner;

public class recurse {
        public static void main (String[] args) {
               
                System.out.println("Writes word vertically");
                
                Scanner user_input = new Scanner(System.in);
               
                String word;
        		System.out.print("Type word here: ");
        		word = user_input.next();
        		
        		vertical(length(word), word);
        }
       //the methods first, rest, and length were taken from the textbook as part of this exercise
        
       // first: returns the first character of the given String
        public static char first(String s) {
            return s.charAt(0);
        }
        // last: returns a new String that contains all but the
        // first letter of the given String
        public static String rest(String s) {
            return s.substring(1, s.length());
        }
        // length: returns the length of the given String
        public static int length(String s) {
            return s.length();
        }
        
        public static void vertical(int n, String s) {
            if (n == 1) {
                System.out.println(first(s));
            } else {
                System.out.println(first(s));
                s = rest(s);
                vertical(n-1, s);
            }
        }
}