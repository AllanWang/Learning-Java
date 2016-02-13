package comp202;
//Allan Wang 260667681


import java.util.ArrayList;

public class ArgsArray {
	public static void main (String[] args) {
		//initialize variables; here are examples you may use
		int[] a = {1, 3, 1, 6, 7, 8, 9, 10};
		int n = 10;
		
		if (true) { //for using args input; if you don't want this, turn it off
			if (args.length == 2) { //checks if there are two inputs
				try {
					n = Integer.parseInt(args[0]); //attempts to parse first input as integer
					if (args[1].startsWith("{") && args[1].endsWith("}")) { //checks if second input has correct format
						try {
							a = list2array(string2list(args[1])); //attempts to create array
			            } catch (NumberFormatException e) { //user did not give an integer (or gave too long an integer)
							badInput(3); //array is not correctly formatted
							return;
			            }
						a = list2array(string2list(args[1])); //attempts to create array
					} else {
						badInput(3); //array is not correctly formatted
						return;
					}
	            } catch (NumberFormatException e) { 
					badInput(2); //user did not give an integer (or gave too long an integer)
					return;
	            }
			} else {
				badInput(1); //did not find two arguments
				return;
			}
		}
		//findMissingNum(n, a); //executes method to find missing numbers
		//do the rest of your stuff in the method findMissingNum
	}
	
	public static void badInput(int i) {
		if (i == 1) { //2 arguments not found
			System.out.println("Make sure your input contains two items; a number and an integer array. Each item should be enclosed with quotations.");
		}
		if (i == 2) {
			System.out.println("Your first input is either not an integer or too big to be stored as an integer");
		}
		if (i == 3) { //issue with array
			System.out.println("Make sure the array integers are separated by commas and that the array itself is enclosed with braces.");
			System.out.println("ie {1, 3, 5, 2}");
		}
	}
	
	//gets an arraylist from the formatted string
	public static ArrayList<Integer> string2list(String s) {
		ArrayList<Integer> list = new ArrayList<>();
		s = s.substring(1, s.length()-1) + ","; //remove braces and add comma to very end
		String item = new String(); //used to store individual items
		while (s.length() > 1) { //make sure the string is not just the last comma
			item = s.substring(0, s.indexOf(',')); //get substring before first comma
			s = s.substring(item.length() + 1); //remove substring along with leading comma
			item = item.replaceAll("\\s",""); //remove all spaces
			list.add(Integer.parseInt(item)); //add new item as integer to arraylist
		}
		return list;
	}
	
	//simple method to convert the arraylist to an array
	public static int[] list2array(ArrayList<Integer> list) {
		int[] array = new int[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}
	
}
