package comp202;

import java.io.*;
import java.util.HashMap;

public class BallotCounter {
	//quiz 4

	public static void main (String[] args) throws IOException {
		String file = "votes.txt";
		HashMap<String, Integer> colors = new HashMap<String, Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	String value = line.substring(0,line.indexOf("\t"));
		    	Integer count = Integer.parseInt(line.substring(line.indexOf("\t")+1));
		    	
		    	if(!colors.containsKey(value)){
		    		colors.put(value,count);
		          } else {
		            //key is already present
		            Integer temp = colors.get(value);
		            temp = temp + count;
		            colors.put(value, temp);
		          }
		    }
		    System.out.println(colors);
		    String most = "most";
		    Integer mostI = 0;
		    for (String value : colors.keySet()) {
		    	Integer count = colors.get(value);
		    	if (count > mostI) {
		    		mostI = count;
		    		most = value;
		    	} else if (count == mostI) {
		    		System.out.println(most + " equals " + value);
		    	}
		    }
		    System.out.println(most + " " + mostI);
		}
	}
}
