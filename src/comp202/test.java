package comp202;

import java.util.Arrays;

public class test {

	public static void main (String[] args) {
		int[] a = {1, 2, 3, 4, 5};
		int[] b = {6, 2, 8, 9};
//		swapArray(a, b);
//		System.out.println(Arrays.toString(a) + " " + Arrays.toString(b));
		if (a[1] == a[2]) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		int c = (int)-3.5;
		Double d = 5.0;
		double cd = d * 3;
//		System.out.println(c);
	}
	
	public static void swapArray(int[] a, int[] b) {
		
		int[] temp = {1, 2};
//		temp[2] = 9;
//		System.out.println(Arrays.toString(a));
		a = temp;
//		System.out.println(Arrays.toString(a));

		b = temp;
		
//		a[0] = b[0];
		
	}
}
