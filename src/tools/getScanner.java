package tools;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by 7681 on 2015-12-23.
 */
public class GetScanner {
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

    public static char getChar(Scanner scanner, String prompt) {
        char c = '$'; //value will be parsed again
        boolean errorAlert = true;
        while(errorAlert) {
        	errorAlert = false;
            System.out.print(prompt);
            String s = scanner.next();
            if (s.length() > 1) {
                System.out.println("Please input one character only!");
                errorAlert = true;
            } else {
                c = s.charAt(0); //get character from String
            }
        }
        return c;
    }
    
    public static BigInteger getBigInteger (Scanner scanner, String prompt) {
    	BigInteger input = BigInteger.valueOf(777); //value will be parsed again
        boolean errorAlert = false;
        while(!errorAlert) {
            errorAlert = true;
            try {
                System.out.print(prompt);
                input = new BigInteger(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number.");
                errorAlert = false;
            }
        }
        return input;
    }
    
    public static Long getLong (Scanner scanner, String prompt) {
    	Long input = (long) 777; //value will be parsed again
        boolean errorAlert = false;
        while(!errorAlert) {
            errorAlert = true;
            try {
                System.out.print(prompt);
                input = Long.parseLong(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Input must be an integer.");
                errorAlert = false;
            }
        }
        return input;
    }

}
