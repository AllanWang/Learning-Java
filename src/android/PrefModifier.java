package android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import web.GetCSSTheme;

public class PrefModifier {
	static String dir;
	static StringBuilder fullText = new StringBuilder();
	static StringBuilder result = new StringBuilder();
	static String incomingFile = "\\pref4M.txt";
	private static final String[] tags = 
		{"theme", "link", "conversation divider", "selected conversation", "status bar", "navigation bar", "text", "text sent", "text received", "date", "date sent", "date received", "message entry", "message hint", "drawer background", "conversation text", "divider", "background", "cancel batch", "info batch", "delete batch", "compose", "delayed sending circle", "delayed sending progress", "actionbar title", "actionbar subtitle"};

	public static void main (String[] args) {
		dir = new File(GetCSSTheme.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
		dir = dir.replace("%20", " ");
//		readFile();
		System.out.println(bitmap("S", "DD"));
//		generatePrefColorUtils();
//		System.out.println(ripple("test", "#ddd"));
	}
	
	public static void readFile() {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(dir + incomingFile))) {
		    while ((line = br.readLine()) != null) {
		       // process the line.

String before = line.substring(0, line.lastIndexOf(", "));
System.out.println(before + "))");
		    }
		} catch (IOException e) {
			System.out.println("ERROR :" + e + "\n" + line);
		}
	}
	
	 public static String bitmap(String drawableName, String tint) {
	        StringBuilder text = new StringBuilder();
	        text.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<bitmap xmlns:android=\"http://schemas.android.com/apk/res/android\"")
	                .append("\nandroid:src=\"@drawable/").append(drawableName).append("\"\nandroid:tint=\"").append(tint).append("\" />");

	        return text.toString();
	    }

	
	
}
