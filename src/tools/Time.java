package tools;

import java.util.Scanner;

public class Time {
	public int hour, minute, second;
	
	public Time() {
		this.hour = 0;
		this.minute = 0;
		this.second = 0;
	}
	
	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public static void getTime(Time t, Scanner scanner, String prompt) {
        boolean errorAlert = true;
        while(errorAlert) {
            errorAlert = false;
            System.out.print(prompt);
            String s = scanner.next();
            if (s.contains(":")) {
            	String[] parts = s.split(":");
            	try
                {
            		t.hour = Integer.parseInt(parts[0]);
                	t.minute = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                	System.out.println("I didn\'t catch the number! Please enter the time as hh:mm:ss.");
                	errorAlert = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                	System.out.println("I didn\'t catch the number! Please enter the time as hh:mm:ss.");
                	errorAlert = true;
                }
            	try
                {
                	t.second = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                	System.out.println("I didn\'t catch the number! Please enter the time as hh:mm:ss.");
                	errorAlert = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                	
                }
         
            } else {
            	System.out.println("I didn\'t catch the number! Please enter the time as hh:mm:ss.");
            	errorAlert = true;
            }
        }
	}

	public static int t2s(Time t) {
		int s = t.hour * 3600 + t.minute * 60 + t.second;
		return s;
	}
	
	public static Time s2t(int s) {
		Time t = new Time();
		t.hour = s/3600;
		t.minute = (s % 3600)/60;
		t.second = s % 60;
		return t;
	}
	
	public static Time addTime(Time t1, Time t2) {
		int sum = t2s(t1) + t2s(t2);
		return s2t(sum);
	}
	
	public static void printTime(Time t) {
		String min = String.valueOf(t.minute);
		if (t.minute < 10) {
			min = String.format("%02d\n", t.minute);
		}
		String sec = String.valueOf(t.second);
		if (t.second < 10) {
			sec = String.format("%02d\n", t.second);
		}
		System.out.println(t.hour + ":" + min + ":" + sec);
	}
}
