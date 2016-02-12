package tools;

public class ArrayModifiers {

	public static int[] addLeadingZero(int[] intArray) {
		int[] intArray2 = new int[intArray.length + 1];
		for(int i = 0; i < intArray.length; i++) {
			intArray2[i+1] = intArray[i];
		}
		intArray2[0] = 0;
		return intArray2;
	}
	
	public static int[] removeLeadingZero(int[] intArray) {
		if (intArray[0] == 0) {
			int[] intArray2 = new int[intArray.length - 1];
			for(int i = 0; i < intArray.length-1; i++) {
				intArray2[i] = intArray[i+1];
			}
			return intArray2;
		}
		return intArray;
	}
	
}
