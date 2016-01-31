package comp202;

public class Lecture8 {
//2016/01/22
	public static void main (String args[]) {
		printMonotonic(isMonotonic(1, 2, 3));
		printMonotonic(isMonotonic(1, 5, 3));
		printMonotonic(isMonotonic(33, 5, 3));
	}
	
	public static boolean isMonotonic (int x, int y, int z) {
		return (x<y == y<z); //returns true if values are in ascending or descending order
	}
	
	public static void printMonotonic (boolean b) {
		if (b) {
			System.out.println("The values given are monotonic");
		} else {
			System.out.println("The values given are not monotonic");
		}
	}
}
