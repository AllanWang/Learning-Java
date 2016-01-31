package comp202;

public class ParseDigits{
  public static void main (String[] args) {
//    int number = Integer.parseInt(args[0]);
    int number = 17365;
    int first = number%10;
    int second = (number-first) % 100/10;
    int third = (number-first-second) % 1000/100;
    int fourth = (number-first-second-third) % 10000/1000;
    int fifth = (number-first-second-third-fourth) % 100000/10000;
    System.out.println(first + " " + second + " " + third + " " + fourth + " " + fifth + " ");
  }
}