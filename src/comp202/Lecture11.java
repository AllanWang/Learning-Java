package comp202;

public class Lecture11 {

	public static void main (String[] args) {
		System.out.println(skipPrint2(3, 20));
	}
	
	//method that takes ints k, n as input
	//prints all numbers from 1 to n that are not multiples of k
	public static void skipPrint (int k, int n) {
		if (n < 1) {
			System.out.println("n must be an integer greater than or equal to 1.");
		}
		int i = 1;
		while (i <= n) {
			if (i % k != 0) {
				System.out.println(i);
			}
			i++;
		}
	}
	
	//method that takes ints k and n as input
	//returns a string and skips multiples of k
	public static String skipPrint2 (int k, int n) {
		if (n < 1) {
			System.out.println("n must be an integer greater than or equal to 1.");
		}
		String s = new String();
		int i = 1;
		while (i <= n) { //alternatively, you could write for(int i = 1; i <=n; i++)
			if (i % k != 0) {
				s = s + i + ", ";
			}
			i++;
		}
		s = s.substring(0, s.length()-2); //removes the last unnecessary ", " (two characters)
		return s;
	}
}
