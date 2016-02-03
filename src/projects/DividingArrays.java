package projects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DividingArrays {
	
	static boolean verboseLog = true;
	private static Random rnd;

	public static void main (String[] args) {
		int recursion = 0;
		
		//how many times do you want to repeat this?
		recursion = 9;
		
		int dividend = 119628;
		int divisor = 448;
		/* Bad divisions
		 119628 / 448
		 447543 / 278
		 649290 / 217
		*/
		
		if (recursion > 0) {
			rnd = new Random();
			if (recursion > 3) {
				verboseLog = false;
			}
			for(int i = 0; i < recursion; i++) {
				int[] dividendArray = int2array(randomInt(6));
				int[] divisorArray = int2array(randomInt(3));
		
				divideArrays(dividendArray, divisorArray);
			}
		} else {
			int[] dividendArray = int2array(dividend);
			int[] divisorArray = int2array(divisor);
	
			divideArrays(dividendArray, divisorArray);
		}
	
	}
	
	public static void divideArrays(int[] dividend, int[] divisor) {
		List<Integer> quotient = new ArrayList<>();
		int[] dividendOrig = duplicateArray(dividend); //saves original dividend as new object
		int[] divisorOrig = duplicateArray(divisor);
		int[] divisor2 = duplicateArray(divisor);
		boolean leadingZeroFlag = false;
		boolean firstLeadingZero = true;
		boolean firstZero = false;
		
		log("Starting dividend", dividend);
		log("Starting divisor", divisor2);
		
		fullLoop:
		while(dividend.length >= divisor2.length) {
			
			if (isDivisible(dividend, divisor2)) { //checks if numbers are aligned and divisible
				int i = 1;
				
				if (divisor2[0] < 5) { //division will not be greater than 2 if first digit of divisor >= 5
					for(; isDivisible(dividend, divisor2); i++) {
						for(int j = 0; j < divisor.length; j++) {
							divisor2[j] = divisor2[j] + divisor[j];
							normalize(divisor2);
						}
					}
					i--; //i is one too many as i++ happens again before condition is checked as false
					//recalculate divisor2
					
					for(int j = 0; j < divisor.length; j++) {
						divisor2[j] = divisor[j] * i;
					}
					divisor2 = normalize(divisor2);	
				}
				
				log("quotient before i " + quotient);
				log("i value " + i);
				quotient.add(i);
				log("quotient after i " + quotient);
				
				log("Modified dividend", dividend);
				log("Modified divisor", divisor2);
				for(int j = 0; j < divisor.length; j++) { //change dividend to new dividend
					dividend[j] = dividend[j] - divisor2[j];
					if (array2int(dividend) == 0) {
						log("Breaking out of fullLoop");
						break fullLoop;
					}
				}
//				firstZero = true;
//				log("New dividend", dividend);
				dividend = removeLeadingZero(normalize(dividend));
				log("Final dividend", dividend);
				
				while (dividend[0] == 0) {
					log("leading zero found");
					dividend = removeLeadingZero(dividend);
//					if (firstZero) { //if there's one zero in the front, that's natural
//						log("first zero now false");
//						firstZero = false;
						//TODO check
//						if (dividend[1] != 0) {
//							quotient.add(0);
//							log("adding zero to quotient");
//						}
//					if (leadingZeroFlag) { //added zero yourself, should not count
//						log("leading zero flag now false");
//						leadingZeroFlag = false;
//					} else {
						quotient.add(0);
						log("adding zero to quotient");
//					}
					
				}
				
				//restore original values
				divisor = duplicateArray(divisorOrig);
				divisor2 = duplicateArray(divisorOrig);
				firstLeadingZero = true;
			} else {
				divisor2 = addLeadingZero(divisor2);
				divisor = addLeadingZero(divisor);
				leadingZeroFlag = true;
				log("added leadingZeroFlag");
				log("new divisor", divisor2);
				if(!firstLeadingZero) {
					quotient.add(0);
				}
				firstLeadingZero = false;
			}
		}
		
//		} //test
		print("Dividend", dividendOrig);
		print("Divisor", divisorOrig);
		quotient = removeTrailingZero(quotient);
		System.out.println("Quotient: " + removeTrailingZero(quotient));
		System.out.println("True Quotient: " + array2int(dividendOrig)/array2int(divisor));
		if(array2int(quotient) == array2int(dividendOrig)/array2int(divisor)) {
			if (array2int(dividend) == 0) {
				System.out.println("Remainder: 0");
			} else {
				print("Remainder", removeLeadingZero(dividend));
			}
			System.out.println("True Remainder: " + array2int(dividendOrig)%array2int(divisor));
			if(array2int(dividend) != array2int(dividendOrig)%array2int(divisor)) {
				System.out.println("--------------INCORRECT--------------");
			}
		} else {
			System.out.println("--------------INCORRECT--------------");
		}
		
	}
	
	public static boolean isDivisible(int[] dividend, int[] divisor) {
		for(int i = 0; i < divisor.length; i++) {
			if (dividend[i] < divisor[i]) {
				return false;
			} else if (dividend[i] > divisor[i]) {
				return true;
			}
		}
		return true;
	}
	
	public static int[] addLeadingZero(int[] intArray) {
		int[] intArray2 = new int[intArray.length + 1];
		for(int i = 0; i < intArray.length; i++) {
			intArray2[i+1] = intArray[i];
		}
		intArray2[0] = 0;
//		log("New array with new zero " + array2string(intArray2));
		return intArray2;
	}
	
	public static int[] removeLeadingZero(int[] intArray) {
		if (intArray[0] == 0) {
			int[] intArray2 = new int[intArray.length - 1];
			for(int i = 0; i < intArray.length-1; i++) {
				intArray2[i] = intArray[i+1];
			}
			log("after removed 0", intArray2);
			return intArray2;
		}
		return intArray;
	}
	
	public static List<Integer> removeTrailingZero(List<Integer> intArrayList) {
		if (intArrayList.get(intArrayList.size()-1) == 0) {
			intArrayList.remove(intArrayList.size()-1);
		}
		return intArrayList;
	}
	
	public static int[] normalize(int[] intArray) {
		boolean doubleDigit = true;
		while(doubleDigit) { //loops around until it guarantees that every digit is 9 or under
			boolean change = false;
			for(int i = 0; i < intArray.length; i++) {
				if (intArray[i] > 9) {
					intArray[i-1] = intArray[i-1] + intArray[i]/10;
					intArray[i] %= 10;
					change = true;
				} else if (intArray[i] < 0) {
					intArray[i] += 10;
					intArray[i-1]--;
					change = true;
				}
			}
			doubleDigit = change;
		}
		return intArray;
	}
	
	public static int[] duplicateArray(int[] intArray) {
		int[] intArray2 = new int[intArray.length];
		for(int i = 0; i < intArray.length; i++) {
			intArray2[i] = intArray[i];
		}
		return intArray2;
	}
	
	public static int array2int (int[] intArray) {
		int result = 0;
		for (int i=0; i<intArray.length; i++) {
		    result += (intArray[intArray.length-i-1]*Math.pow(10, i));
		}
		return result;
	}
	
	public static int array2int (List<Integer> list) {
		int total = 0;
		for (Integer i : list) { // assuming list is of type List<Integer>
		    total = 10*total + i;
		}
		return total;
	}
	
	public static int[] int2array (int integer) {
		String sInt = String.valueOf(integer);
		int[] intArray = new int[sInt.length()];
		for(int i = 0; i < intArray.length; i++) {
			intArray[i] = Character.getNumericValue(sInt.charAt(i));
		}
		return intArray;
	}
	
	public static String array2string (int[] intArray) {
		StringBuilder strNum = new StringBuilder();
		
		for (int num : intArray) {
			strNum.append(num);
		}
		
		return strNum.toString();
	}
	
	public static void print (String text, int[] intArray) {
		System.out.println(text + ": " + array2string(intArray));
	}
	
	public static void log (String text, int[] intArray) {
		if (verboseLog) {
			System.out.println(text + ": " + array2string(intArray));
		}
	}
	
	public static void log (String text) {
		if (verboseLog) {
			System.out.println("\t" + text);
		}
	}

	public static int randomInt (int digCount) {
		StringBuilder sb = new StringBuilder(digCount);
	    for(int i=0; i < digCount; i++)
	        sb.append((char)('0' + rnd.nextInt(10)));
	    return Integer.parseInt(sb.toString());
	}
}
