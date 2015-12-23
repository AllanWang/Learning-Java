package projects;

import java.util.Scanner;

public class WatsonCrick {
    public static void main(String[] args){
    
        Scanner user_input = new Scanner(System.in);
               
        String DNA;
		System.out.print("DNA sequence: ");
		DNA = user_input.next();
        DNA = DNA.toLowerCase();
		System.out.println(DNAc(DNA));
		
        user_input.close(); // We are done with the scanner now.
    }
    
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
    public static String DNAc(String DNA){
        String x = new String();
        while (length(DNA) > 0) {
            if (DNA.startsWith("a")){
                x = x + "T";
            } else if (DNA.startsWith("c")){
                x = x + "G";
            } else if (DNA.startsWith("t")){
                x = x + "A";
            } else if (DNA.startsWith("g")){
                x = x + "C";
            } else {
                String no = first(DNA) + " is not a valid nucleotide.";
                return no;
            }
            DNA = rest(DNA);
        }
        String yes = "The complement of the sequence is " + x;
        return yes;
    }
    
}