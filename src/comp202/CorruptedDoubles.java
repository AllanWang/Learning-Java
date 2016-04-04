package comp202;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CorruptedDoubles {
	//quiz 4
	
	public static void main (String[] args) throws IOException {
		String file = "mystery.txt";
		double sum = 0.00;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	Boolean valid = true;
		    	Boolean decimal = false;
		    	for (int i = 0; i < line.length(); i++) {
		    		if (line.charAt(i) <= '9' && line.charAt(i) >= '0') {
		    			//do nothing
		    		} else if (line.charAt(i) == '.') {
		    			if (decimal) {
		    				valid = false;
		    			} else {
		    				decimal = true;
		    			}
		    		} else {
		    			valid = false;
		    		}
		    	}
		    	if (valid) {
//		    		int index = line.indexOf(".") + 2;
////		    		System.out.println("index " + index);
//		    		int last = Character.getNumericValue(line.charAt(index));
////		    		System.out.println(last);
////		    		System.out.println("third " + line.charAt(line.indexOf(".") + 3));
//		    		if (line.charAt(line.indexOf(".") + 3) >= '5') {
//
//		    			last++;
//		    		}
//		    		line = line.substring(0, line.indexOf(".") + 2) + last;
//		    		System.out.println("line " + line);
		    		sum += Double.parseDouble(line);
		    	}
		    }
		    System.out.println(sum);
		}
	}
}
