package projects;

import java.util.Scanner;

import static tools.getScanner.getInt;

public class LeapYearCalc2 {
    public static void main(String[] args){
    
        Scanner user_input = new Scanner(System.in);
               
        int year = getInt(user_input, "Year: ");
                           
        user_input.close(); // We are done with the scanner now.

        if (isLeapYear(year)) {
            subsequentLeapYear(year);
        } else {
            System.out.println("This is not a leap year");
        }    
    }
    
    public static void subsequentLeapYear(int year){
        year = year + 4;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
            System.out.println("The next leap year is " + year + ".");
        }else{
            year = year + 4;
            System.out.println("The next leap year is " + year + ".");
        }
    }
    
    public static boolean isLeapYear(int year){
    
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
            return true;
        }else{
            return false;
        }
    }
    
}