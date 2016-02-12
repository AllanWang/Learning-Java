package projects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DividingArrays {
	
	static boolean verboseLog = true;
	private static Random rnd;
	static int base = 10;

	public static void main (String[] args) {
		int recursion = 0;
		
		//how many times do you want to repeat this?
//		recursion = 9;
		
		int dividend = 729110;
		int divisor = 680;
		/* Previous fixes
		 * 156753 294
		 * 653097 168
		*/
		/* Incorrect
		 * 326059 329
		 */
		
		if (recursion > 0) {
			rnd = new Random();
			if (recursion > 3) {
				verboseLog = false;
			}
			for(int i = 0; i < recursion; i++) {
				int[] dividendArray = int2array(randomInt(6));
				int[] divisorArray = int2array(randomInt(3));
				print("Original dividend", dividendArray);
				print("Original divisor", divisorArray);
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
		int leadingZeroCount = 0;
		boolean firstLeadingZero = true;
		boolean firstZero = false;
		boolean dividendZero = false;
		boolean alreadyRemoved = false;
		
		log("Starting dividend", dividend);
		log("Starting divisor", divisor2);
		
		fullLoop:
		while(dividend.length >= divisor2.length) {
			
			if (isDivisible(dividend, divisor2)) { //checks if numbers are aligned and divisible
				int i = 1;
				dividendZero = false;
				alreadyRemoved = false;
				if (divisor2[0] < 5) { //division will not be greater than 2 if first digit of divisor >= 5
					for(; isDivisible(dividend, divisor2); i++) {
						for(int j = 0; j < divisor.length; j++) {
							divisor2[j] = divisor2[j] + divisor[j];
//							print("eee", divisor2);
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
				if (dividend.length > divisor.length) {
					//restore divisor 
					divisor = duplicateArray(divisorOrig);
					divisor2 = duplicateArray(divisorOrig);
					
					dividend = normalize(dividend);
					if (dividend[0] == 0) {
						dividend = removeLeadingZero(dividend);
						log("already removed true");
						alreadyRemoved = true;
					}

					log("Final dividend", dividend);
					
					while (dividend[0] == 0) {
						log("leading zero found", dividend);
						dividend = removeLeadingZero(dividend);
						if (!firstZero) {
							log("prev quotient ee " + quotient);
							quotient.add(0);
							log("new quotient ee " + quotient);
						}
						dividendZero = true;
					}
				}
				
				//restore original booleans
				firstLeadingZero = true;
				firstZero = false;
			} else {
				divisor2 = addLeadingZero(divisor2);
				divisor = addLeadingZero(divisor);
//				leadingZeroCount++;
//				log("added leadingZeroFlag");
				log("new divisor", divisor2);
//				if((quotient.size() != 0 && !firstLeadingZero) || alreadyRemoved) {
				if((quotient.size() != 0 && !firstLeadingZero)) {
					quotient.add(0);
				} else if (quotient.size() == 0) {
					firstZero = true;
				}
				alreadyRemoved = false;
				firstLeadingZero = false;
			}
		}
		
//		} //test
		print("Dividend", dividendOrig);
		print("Divisor", divisorOrig);
		if (!dividendZero) {
			quotient = removeTrailingZero(quotient);
			System.out.println("removing trailing quotient zero");
		}
		System.out.println("Quotient: " + quotient);
		System.out.println("True Quotient: " + array2int(dividendOrig)/array2int(divisor));
		if(array2int(quotient) == array2int(dividendOrig)/array2int(divisor)) {
			if (array2int(dividend) == 0) {
				System.out.println("Remainder: 0");
			} else {
				dividend = normalize(dividend);
				while (dividend[0] == 0) {
					verboseLog = false;
					dividend = removeLeadingZero(dividend);
				}
				print("Remainder", dividend);
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
		} else {
			log("no leading zero");
		}
		return intArray;
	}
	
	public static List<Integer> removeTrailingZero(List<Integer> intArrayList) {
		if (intArrayList.get(intArrayList.size()-1) == 0) {
			intArrayList.remove(intArrayList.size()-1);
		} else {
			log("no leading zero");
		}
		return intArrayList;
	}
	
	public static int[] normalize(int[] intArray) {
		boolean doubleDigit = true;
//		print("asdf", intArray);
		while(doubleDigit) { //loops around until it guarantees that every digit is 9 or under
			boolean change = false;
			for(int i = 1; i < intArray.length; i++) { //i starts at 1, not 0, because first digit cannot be normalized
				if (intArray[i] > base - 1) {
					intArray[i-1] = intArray[i-1] + intArray[i]/base;
					intArray[i] %= base;
					change = true;
				} else if (intArray[i] < 0) {
					intArray[i] += base;
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
		    result += (intArray[intArray.length-i-1]*Math.pow(base, i));
		}
		return result;
	}
	
	public static int array2int (List<Integer> list) {
		int total = 0;
		for (Integer i : list) { // assuming list is of type List<Integer>
		    total = base*total + i;
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
	        sb.append((char)('0' + rnd.nextInt(base)));
	    return Integer.parseInt(sb.toString());
	}
}
