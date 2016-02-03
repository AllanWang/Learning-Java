package comp202;

public class Lecture10 {
	//2016/01/27
	
	public static void main (String[] args) {
		if (args.length == 3) {
			printValueMethod(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		}
		
		printMultiples(3, 87);
		printBlastoff(10);
		
	}
	
	//this method returns true if a = c, b = c, or if a + b = c
	public static boolean valueMethod (int a, int b, int c) {
		if (a == c || b == c || (a + b) == c) {
			return true;
//		} else if (b == c) {		//another way of evaluating the method is to use multiple if else's
//			return true;
//		} else if ((a + b) == c) {
//			return true;
		}
		return false;
	}
	
	public static void printValueMethod (int a, int b, int c) {
		if (valueMethod(a, b, c)) {
			System.out.println("One of the conditions in valueMethod was met.");
		} else {
			System.out.println("None of the conditions in valueMethod were met.");
		}
	}
	
	public static void printBlastoff(int x) { //adds a countdown from the given value
		if (x <=0) {
			System.out.println("Invalid integer");
		}
		//add sleep so that the countdown actually happens in one second intervals
		for(;x>0;x--) {
			try {
			    Thread.sleep(1000);
			    System.out.println(x + "...");
			} catch(InterruptedException ex) {}
		}
		
		try {
		    Thread.sleep(1000);
		    System.out.println("Blastoff!");
		} catch(InterruptedException ex) {}

	}
	
	public static void printMultiples(int a, int b) {
		int a2 = a; //save original increments
		while (a <= (b - a2)) {
			System.out.print(a + ", ");
			a += a2;
		}
		System.out.println(a); //terminate the line and do not add a comma at the end
	}
}
