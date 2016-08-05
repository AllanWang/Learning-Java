package web;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrefKey {
	static HashMap<String, HashMap<String, HashSet<String>>> valueSet = new HashMap<String, HashMap<String, HashSet<String>>>();
		static String dir;
		static StringBuilder fullText = new StringBuilder();
		static StringBuilder result = new StringBuilder();
		static String incomingFile = "\\pref.txt";
		static String pattern = "public int (get.+?)\\(.+?return getInt\\((.+?),";
		static String patternn = "public void (set.+?)\\(.+?setInt\\((.+?),";
		static String pattern2 = "\\}([^\\}]+?)\\{[^\\{]*?(background\\:|color\\:|background\\-color\\:)([^\\{]+?)[;,\\}]";
	public static void main (String[] args) {
		dir = new File(PrefKey.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
		dir = dir.replace("%20", " ");
//		getThemeComponents(dir + "\\css.txt");
		readFile();
		setContent();
//		getContent();
//		formatResult();
//		writeFile();
	}
	
	public static void getContent() {
		Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
		Matcher m = r.matcher(fullText);
	      if (m.find()) {
	         do {
//	        	 System.out.println("1: " + m.group(1));
//	        	 System.out.println("2: " + m.group(2));
//	        	 System.out.println("3: " + m.group(3));
//	        	 System.out.println(m.group(1) + " " +  m.group(2));
	        	 System.out.println("case PreferenceUtils." + m.group(2) + ":");
	        	 System.out.println("prefUtils." + m.group(1) + "();");
	         } while (m.find()); //go back one line
	      } else {
	         System.out.println("NO MATCH");
	      }
	      
//		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir + "\\new_css.txt"), "utf-8"))) {
//			writer.append(result);
//			if (writer != null) {
//				writer.close();
//			}
//		} catch (Exception e) {
//			// This should never happen
//			e.printStackTrace();
//		}
	}
	
	public static void setContent() {
		Pattern r = Pattern.compile(patternn, Pattern.DOTALL);
		Matcher m = r.matcher(fullText);
	      if (m.find()) {
	         do {
	        	 System.out.println("case PreferenceUtils." + m.group(2) + ":");
	        	 System.out.println("prefUtils." + m.group(1) + "(i);");
	        	 System.out.println("return;");
	         } while (m.find()); //go back one line
	      } else {
	         System.out.println("NO MATCH");
	      }
	}
	
	private static void addValues(String values, String type, String color) {
		if (color.length() == 4 && color.startsWith("#")) {
//			System.out.println("Color: " + color);
			color = color.replaceAll("#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])","#$1$1$2$2$3$3");
		}
		String[] valueList = values.split(",");
		for (String v : valueList) {
			v = v.trim() + ",";
			if (valueSet.containsKey(type)) {
				if (valueSet.get(type).containsKey(color)) {
					valueSet.get(type).get(color).add(v);
				} else {
					HashSet<String> newSet = new HashSet<String>();
					newSet.add(v);
					valueSet.get(type).put(color, newSet);
				}
			} else {
				HashSet<String> newSet = new HashSet<String>();
				newSet.add(v);
				HashMap<String, HashSet<String>> newMap = new HashMap<String, HashSet<String>>();
				newMap.put(color, newSet);
				valueSet.put(type, newMap);
			}
		}
	}
	
	private static void formatResult() {
		for (String k1 : valueSet.keySet()) {
			result.append("/* ").append(k1).append(" */\n");
			for (String k2 : valueSet.get(k1).keySet()) {
//				result.append("\t" + k2 + "\n");
				for (String k3 : valueSet.get(k1).get(k2)) {
					result.append("\t\t" + k3 + "\n");
				}
			}
			result.append("\n\t{\n\t\t").append(k1).append(" #f0f !important;\n\t}\n\n");
		}
	}
	public static void readFile() {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(dir + incomingFile))) {
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	fullText.append(line);
		    }
		} catch (IOException e) {
			System.out.println("ERROR :" + e + "\n" + line);
		}
	}
	
	public static void writeFile() {
		try {
			File newFile = new File(dir, "css_new.txt");
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
