package projects;

import java.util.Scanner;

public class recurse2 {
        public static void main (String[] args) {
               
                System.out.println("Writes word vertically");
                
                Scanner user_input = new Scanner(System.in);
               
                String word;
        		System.out.print("Type word here: ");
        		word = user_input.next();
        		
        		vertical(length(word), length(word), word);
        }
        //same function as recurse.java but with personal modifications
        
       // first: returns the first character of the given String
        public static void vertical(int m, int n, String s) {
            int c = m - n;
            if (c == m - 1) {
                System.out.println(s.charAt(c));
            } else {
                System.out.println(s.charAt(c));
                n = n - 1;
                vertical(m, n, s);
            }
        }
        // length: returns the length of the given String
        public static int length(String s) {
            return s.length();
        }
       
}