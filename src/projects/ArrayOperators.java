package projects;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ivon on 2/2/2016.
 */
public class ArrayOperators {

	private static Random rnd;
	static int base = 10;
	static boolean errorChecker = false;
	static boolean errorMarker = false;
	
    public static void main(String[] args) {
		int recursion = 0;
		
		recursion = 10; //how many times do you want to repeat this?
		base = 10; //what base?
		
		int dividend = 729110; //testing specific values, comment out "recursion = ..." to use this
		int divisor = 680;

		if (recursion > 0) {
			if (recursion > 10) {
				errorChecker = true;
			}
			rnd = new Random();
			for(int i = 0; i < recursion; i++) {
				base = randomFromRange(2, 10);
				int[] dividendArray = int2array(randomInt(randomFromRange(4, 8)));
				int[] divisorArray = int2array(randomInt(randomFromRange(1, 4)));
				printResult (dividendArray, divisorArray);
			}
		} else {
			int[] dividendArray = int2array(dividend);
			int[] divisorArray = int2array(divisor);
			printResult (dividendArray, divisorArray);
		}
		
		if (!errorMarker) {
    		System.out.println("No errors!");
    	}
        
    }

    /****************************/
    /*** Arithmetic Operators ***/
    /****************************/

    /**
     * Divide a by b
     */
    private static int[] divide(int[] a, int[] b) {
        if (isLess(a, b)) {
            return new int[] {0};
        } else if (isEqual(a, b)) {
            return new int[] {1};
        } else {

            int qLength = a.length - b.length + 1;
            int p = b.length - 1;   // So that x is the same length as b
            int[] x = subArray(a, 0, p);
            while (isGreater(b, x)) {
                p++;
                qLength--;
                x = subArray(a, 0, p);
            }
            int[] q = new int[qLength];

            for (int i=0; i<q.length; i++) {
                int c = 1;
                int[] y = multiply(b, c);
                while (isLess(y, x) || isEqual(y, x)) {
                    c++;
                    y = multiply(b, c);
                }
                q[i] = c - 1;
                x = subtract(x, multiply(b, c-1));
                if (p+i+1 < a.length) {
                    x = append(x, a[p+i+1]);
                }
            }

            return q;

        }
    }

    /**
     * Subtract b from a
     */
    private static int[] subtract(int[] a, int[] b) {
        if (isLess(a, b)) {
            return subtract(b, a);
        }
        int[] d = new int[a.length];
        for (int i=0; i<d.length; i++) {
            int ia = a.length - i - 1;
            int ib = b.length - i - 1;
            int id = d.length - i - 1;
            if (ib >= 0) {
                d[id] = a[ia] - b[ib];
            } else {
                d[id] = a[ia];
            }
        }
        return beautify(d, base);
    }

    /**
     * Multiply a by k
     */
    private static int[] multiply(int[] a, int k) {
        int[] p = new int[a.length];
        for (int i=0; i<p.length; i++) {
            p[i] = a[i]*k;
        }
        return beautify(p, base);
    }

    /****************************/
    /*** Relational Operators ***/
    /****************************/

    /**
     * Tells you if a is equal to b
     */
    private static boolean isEqual(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        } else {
            for (int i=0; i<a.length; i++) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Tells you if a is less than b
     */
    private static boolean isLess(int[] a, int[] b) {
        if (a.length < b.length) {
            return true;
        } else if (a.length > b.length) {
            return false;
        } else {
            for (int i=0; i<a.length; i++) {
                if (a[i] < b[i]) {
                    return true;
                } else if (a[i] > b[i]) {
                    return false;
                }
            }
            return false;
        }
    }

    /**
     * Tells you if a is greater than b
     */
    private static boolean isGreater(int[] a, int[] b) {
        if (a.length > b.length) {
            return true;
        } else if (a.length < b.length) {
            return false;
        } else {
            for (int i=0; i<a.length; i++) {
                if (a[i] > b[i]) {
                    return true;
                } else if (a[i] < b[i]) {
                    return false;
                }
            }
            return false;
        }
    }

    /******************/
    /*** Formatting ***/
    /******************/
    
    /**
     * Ensure that all digits of a are within
     * the range [0, b). Remove all leading zeros.
     *
     * @param b
     *          The base of the number a
     */
    private static int[] beautify(int[] a, int b) {
        a = propagateDigits(a, b);
        return removeLeadingZeros(a);
    }

    /**
     * Ensure that all digits of a
     * are within the range [0, b)
     *
     * @param b
     *          The base of the number a
     */
    private static int[] propagateDigits(int[] a, int b) {
        int[] c = copy(a);
        for (int i=c.length-1; i>=0; i--) {
            if (c[i] >= b) {
                int overflow = c[i] / b;
                if (i > 0) {
                    c[i-1] += overflow;
                } else {
                    c = prepend(c, overflow);
                    i++;
                }
                c[i] = c[i] % b;
            } else if (c[i] < 0) {
                if (i > 0) {
                    int borrow = -((int) Math.floor(((float) c[i]) / b));
                    c[i-1] -= borrow;
                    c[i] += (borrow * b);
                }
            }
        }
        return c;
    }

    /**
     * Remove all leading zeros of a
     */
    private static int[] removeLeadingZeros(int[] a) {
        int actualLength = 0;
        for (int i=0; i<a.length; i++) {
            if (a[i] != 0) {
                actualLength = a.length - i;
                break;
            }
        }
        int[] r = new int[actualLength];
        for (int i=0; i<r.length; i++) {
            r[r.length-i-1] = a[a.length-i-1];
        }
        return r;
    }

    /***********************/
    /*** Array Utilities ***/
    /***********************/
    
    /**
     * Creates a copy of an array
     */
    private static int[] copy(int[] a) {
        int[] b = new int[a.length];
        for (int i=0; i<a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }

    /**
     * Add b to the beginning of a
     */
    private static int[] prepend(int[] a, int b) {
        int[] r = new int[a.length+1];
        r[0] = b;
        for (int i=0; i<a.length; i++) {
            r[i+1] = a[i];
        }
        return r;
    }

    /**
     * Add b to the end of a
     */
    private static int[] append(int[] a, int b) {
        int[] r = new int[a.length+1];
        for (int i=0; i<a.length; i++) {
            r[i] = a[i];
        }
        r[r.length-1] = b;
        return r;
    }

    /**
     * Return sub-array of a from a[start] to a[end], inclusive
     */
    private static int[] subArray(int[] a, int start, int end) {
        int[] s = new int[end - start + 1];
        for (int i=0; i<s.length; i++) {
            s[i] = a[start+i];
        }
        return s;
    }
    
    //Allan's stuff
    
    public static int randomInt (int digCount) {
		StringBuilder sb = new StringBuilder(digCount);
	    for(int i=0; i < digCount; i++)
	        sb.append((char)('0' + rnd.nextInt(base)));
	    return Integer.parseInt(sb.toString());
	}
    
    public static int randomFromRange (int lowest, int highest) {
		int i = lowest + rnd.nextInt(highest-lowest);
	    return i;
	}
    
    public static int[] int2array (int integer) {
		String sInt = String.valueOf(integer);
		int[] intArray = new int[sInt.length()];
		for(int i = 0; i < intArray.length; i++) {
			intArray[i] = Character.getNumericValue(sInt.charAt(i));
		}
		return intArray;
	}
    
    public static int array2int (int[] intArray) {
		int result = 0;
		for (int i=0; i<intArray.length; i++) {
		    result += (intArray[intArray.length-i-1]*Math.pow(base, i));
		}
		return result;
	}
    
    public static void printResult (int[] dividendArray, int[] divisorArray) {
    	if (array2int(divisorArray) == 0) {
    		return;
    	}
    	if (!errorChecker) {
    		System.out.print("Base " + base + ":\t");
    		System.out.print(Arrays.toString(dividendArray) + " / " + Arrays.toString(divisorArray) + " = ");
    		System.out.println(Arrays.toString(divide(dividendArray, divisorArray)));
    		if (array2int(dividendArray)/array2int(divisorArray) != array2int(divide(dividendArray, divisorArray))) {
    			System.out.println("-----INCORRECT-----");
    		}
    	} else if (array2int(dividendArray)/array2int(divisorArray) != array2int(divide(dividendArray, divisorArray))) {
    		System.out.print("Base " + base + ": ");
    		System.out.print(Arrays.toString(dividendArray) + " / " + Arrays.toString(divisorArray) + " != ");
    		System.out.println(Arrays.toString(divide(dividendArray, divisorArray)));
    		errorMarker = true;
		}
    }

}