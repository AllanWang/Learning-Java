package android;

public class PrefGenerator {
	
	private static String[] tags = 
		{"theme", "link", "conversation divider", "selected conversation", "status bar", "navigation bar", "text", "text sent", "text received", "date", "date sent", "date received", "message entry", "message hint", "drawer background", "conversation text", "divider", "background", "cancel batch", "info batch", "delete batch", "compose", "delayed sending circle", "delayed sending progress", "actionbar title", "actionbar subtitle"};

	private static String[] intermediate =
		{"theme", "link", "selected conversation", "status bar", "navigation bar", "text", "date", "message entry", "message hint", "drawer background", "conversation text", "background", "compose", "delayed sending circle", "delayed sending progress", "actionbar title"};
	
	private static String[] basic =
		{"theme", "link", "text", "message entry", "drawer background", "conversation text", "background", "actionbar title"};
	
	private static String[] absolute =
		{"theme", "link", "conversation divider", "selected conversation", "status bar", "navigation bar", "text sent", "text received", "date sent", "date received", "message entry", "message hint", "drawer background", "conversation text", "divider", "background", "cancel batch", "info batch", "delete batch", "compose", "delayed sending circle", "delayed sending progress", "actionbar title", "actionbar subtitle"};

	private static String[] neww = 
		{"evolve msg right", "evolve msg left", "evolve msg locked right", "evolve msg locked left", "evolve msg error right",
				"evolve msg delivered right", "evolve msg sending right", "evolve msg ripple right", "evolve msg ripple left"};
		
	private static String[] boole =
		{"evolve_theme_headers", "evolve_theme_contact_pictures", "evolve_theme_round_contact_pictures", "evolve_theme_show_duplicate_contact_pictures", "evolve_theme_always_show_date", "evolve_theme_message_fill_width"};
	public static void main (String[] args) {
		tags = boole;
		generateB();
//		generatePrefColorUtils();
//		System.out.println(ripple("test", "#ddd"));
	}
	
	private static void generateAttributes() {
		for (String s : tags) {
			s = (s + " color");
			s = ("night " + s);
			
			String key = s.replace(" ", "_").toLowerCase();
			String keyUpper = key.toUpperCase();
			String[] ss = s.split(" ");
			
			StringBuilder title = new StringBuilder();
			for (String sss : ss) {
				title.append(Ul(sss));
			}
			
			System.out.print(keyUpper);
			System.out.print("(\"");
			System.out.print("night_" + title.substring(5, 6).toLowerCase() + title.substring(6));
			System.out.print("\", PreferenceUtils.");
			System.out.print(keyUpper + ", ");
			System.out.print("PreferenceUtils.");
			System.out.print(keyUpper + "_CP, R.string.");
			System.out.print(key);
			System.out.println("),");
		}
	}
	
	private static void generate() {
		for (String s : tags) {
			s = (s + " color");
			s = ("night " + s);
//			s = (s + " cp");
			String key = s.replace(" ", "_").toUpperCase();
			String[] ss = s.split(" ");
			
			StringBuilder title = new StringBuilder("set");
			for (String sss : ss) {
				title.append(Ul(sss));
			}
			
////			print(key);
////			print(" = \"");
////			println(key.toLowerCase() + "\",");
//			
			System.out.println("public void " + title.toString() + "(int i) {");
			System.out.println("\tsetInt(" + key + ", i);");
//			System.out.println("\t" + title.toString().substring(0, title.toString().length()-2) + "(i);");
			System.out.println("}\n");
			
//			System.out.println("ColorAttributes." + key + ", ");
		

		}
	
	}
	
	private static void generateB() {
		for (String s : tags) {
//			String key = s.replace(" ", "_").toUpperCase();
//			String[] ss = s.split(" ");
//			
//			StringBuilder title = new StringBuilder("set");
//			for (String sss : ss) {
//				title.append(Ul(sss));
//			}
			
////			print(key);
////			print(" = \"");
////			println(key.toLowerCase() + "\",");
//			
//		print(s.toUpperCase());
//		print("(\"");
//		print(s);
//		print("\", PreferenceUtils.");
//		print(s.replace("_theme", "").toUpperCase());
//		print(", R.string.");
//		print(s);
//		println("),");
			
			print("BooleanAttributes.");
			print(s.toUpperCase());
			println(", ");

		}
	
	}
	
	private static void generateAbsolute() {
		for (String s : tags) {
			s = ("preferences " + s + " color");
//			s = ("night " + s);
			
			String key = s.replace(" ", "_").toLowerCase();
			System.out.println("<com.pitchedapps.evolvesms.themestore.customViews.ColorPicker");
			System.out.println(" android:id=\"@+id/" + key + "\"");
			System.out.println("android:layout_width=\"match_parent\"");
			System.out.println("android:layout_height=\"wrap_content\" />");
	
		}
	}
	
	private static void generateAbsoluteJava() {
		for (String s : tags) {
			s = (s + " color");
//			s = ("night " + s);
			
			String key = s.replace(" ", "_").toLowerCase();
			System.out.print("createColorPicker(R.id.preferences_");
			System.out.print(key);
			System.out.print(", R.string.");
			System.out.print(key);
			System.out.print(", PreferenceUtils.");
			System.out.print(key.toUpperCase());
			System.out.println("_CP);");
	
	
		}
	}
	
	private static void generateTextStrings() {
		for (String s : tags) {
			s = (s + " color");
//			s = ("night " + s);
			
			String key = s.replace(" ", "_").toLowerCase();
			String[] ss = s.split(" ");
			
			StringBuilder title = new StringBuilder();
			for (String sss : ss) {
				title.append(Ul(sss)).append(" ");
			}
			
			System.out.println("<string name=\"" + key + "\">" + title + "</string>");
		}
	}
	
	private static void generatePrefColorUtils() {
		System.out.println("public int getColor(String key) {\nswitch (key) {\n");
		for (String s : tags) {
			s = (s + " color");
//			s = ("night " + s);
			
			String key = s.replace(" ", "_").toUpperCase();
			String[] ss = s.split(" ");
			
			StringBuilder title = new StringBuilder("get");
			for (String sss : ss) {
				title.append(Ul(sss));
			}
			
			key = (key + "_CP");
			title.append("CP");
			
			System.out.println("case PreferenceUtils." + key + ":");
       	 System.out.println("return prefUtils." + title.toString() + "();");
		}
		for (String s : tags) {
			s = (s + " color");
			s = ("night " + s);
			
			String key = s.replace(" ", "_").toUpperCase();
			String[] ss = s.split(" ");
			
			StringBuilder title = new StringBuilder("get");
			for (String sss : ss) {
				title.append(Ul(sss));
			}
			
			key = (key + "_CP");
			title.append("CP");
			
			System.out.println("case PreferenceUtils." + key + ":");
       	 System.out.println("return prefUtils." + title.toString() + "();");
		}
		System.out.println("default:\nUtils.e(\"Unknown key in PreferenceColorUtils \" + key);\nreturn prefUtils.getInt(key, 0xffff00ff);\n}\n}");
		System.out.println("\n\npublic void setColor(String key, int i) {\nswitch (key) {\n");
		for (String s : tags) {
			s = (s + " color");
//			s = ("night " + s);
			
			String key = s.replace(" ", "_").toUpperCase();
			String[] ss = s.split(" ");
			
			StringBuilder title = new StringBuilder("set");
			for (String sss : ss) {
				title.append(Ul(sss));
			}
			
			key = (key + "_CP");
			title.append("CP");
			
			System.out.println("case PreferenceUtils." + key + ":");
       	 System.out.println("prefUtils." + title.toString() + "(i);");
       	 System.out.println("return;");
		}
		for (String s : tags) {
			s = (s + " color");
			s = ("night " + s);
			
			String key = s.replace(" ", "_").toUpperCase();
			String[] ss = s.split(" ");
			
			StringBuilder title = new StringBuilder("set");
			for (String sss : ss) {
				title.append(Ul(sss));
			}
			
			key = (key + "_CP");
			title.append("CP");
			
			System.out.println("case PreferenceUtils." + key + ":");
       	 System.out.println("prefUtils." + title.toString() + "(i);");
       	 System.out.println("return;");

		}
 		System.out.println("default:\nprefUtils.setInt(key, i);\nreturn;\n}\n}");

	}
	
	private static String Ul(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
	public static String bitmap(String drawableName, String tint) {
        StringBuilder text = new StringBuilder();
        text.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<bitmap xmlns:android=\"http://schemas.android.com/apk/res/android\"")
                .append("\nandroid:src=\"@drawable/").append(drawableName).append("\"\nandroid:tint=\"").append(tint).append("\" />");
        
        return text.toString();
    }
    
    public static String ripple(String drawableName, String ripple) {
        StringBuilder text = new StringBuilder();
        text.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<ripple xmlns:android=\"http://schemas.android.com/apk/res/android\"")
                .append("\nandroid:color=\"").append(ripple).append("\" >\n")
                .append("<item android:drawable=\"@drawable/").append(drawableName).append("\"/>\n</ripple>");

        return text.toString();
    }
    
    private static void print(String s) {
    	System.out.print(s);
    }
    
    private static void println(String s) {
    	System.out.println(s);
    }
}
