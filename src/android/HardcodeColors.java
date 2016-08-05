package android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HardcodeColors {
	static TreeMap<String, String> valueSet = new TreeMap<String, String>();
		static String dir = "C:\\Users\\user1\\PA\\PZ\\theme\\src\\main\\res\\values\\";
		static String fileName = "colors_framework_orig.xml";
		static StringBuilder fullText = new StringBuilder();
		static StringBuilder result = new StringBuilder();
		static String pattern = ">@color\\/(.+?)<\\/color>";
		static String colorMapPattern = "<color name=\"([^\\/]+?)\">#(.{3,8})<\\/color>";
		static String colorHardCodePattern = "<color name=\"([^\\/]+?)\">@color\\/([^\\/]+?)<\\/color>";
	public static void main (String[] args) {
//		dir = new File(HardcodeColors.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
//		dir = dir.replace("%20", " ");
//		getThemeComponents(dir + "\\css.txt");
		readFile();
		getContent();
		edit();
		createNewString();
		writeFile();
	}
	
	public static void getContent() {
		Pattern r = Pattern.compile(colorMapPattern, Pattern.DOTALL);
		Matcher m = r.matcher(fullText);
	      if (m.find()) {
	         do {
	        	 System.out.println("1: " + m.group(1));
	        	 System.out.println("2: " + m.group(2));
	        	 valueSet.put(m.group(1), m.group(2));
	         } while (m.find());
	      } else {
	         System.out.println("NO MATCH");
	      }
	}
	
	
	public static void readFile() {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(dir + fileName))) {
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	fullText.append(line);
		    }
		} catch (IOException e) {
			System.out.println("ERROR :" + e + "\n" + line);
		}
	}
	
	public static void edit() {
		Pattern r = Pattern.compile(colorHardCodePattern, Pattern.DOTALL);
		Matcher m = r.matcher(fullText);
	      if (m.find()) {
	         do {
	        	 System.out.println("1: " + m.group(1));
	        	 System.out.println("2: " + m.group(2));
	        	 if (valueSet.containsKey(m.group(2))) {
	        		 valueSet.put(m.group(1), valueSet.get(m.group(2)));
	        	 } else {
	        		 System.out.println("Missing key " + m.group(2));
	        	 }
//	        	 valueSet.put(m.group(1), m.group(2));
	         } while (m.find());
	      } else {
	         System.out.println("NO MATCH");
	      }
	}
	
	public static void createNewString() {
		result.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>");
		for (String s : valueSet.keySet()) {
//			System.out.println(s + valueSet.get(s));
			result.append("\t<color name=\"")
			.append(s)
			.append("\">#")
			.append(valueSet.get(s))
			.append("</color>\n");
		}
		result.append("</resources>");
	}
	
	public static void writeFile() {
		try {
			File newFile = new File(dir, "new_" + fileName);
			OutputStreamWriter f = new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8");
			f.append(result);
			f.close();
//			writer.append(result);
//			if (writer != null) {
//				writer.close();
//			}
		} catch (Exception e) {
			// This should never happen
			e.printStackTrace();
		}
	}
}
