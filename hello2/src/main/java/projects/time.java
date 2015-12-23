package projects;

public class time {
    public static void main (String[] args) {
        String am, eur, day, month;
		am = "American format:";
		eur = "European format:";
		day = "Saturday";
		month = "July";
		int year, date;
		year = 2011;
		date = 16;
		System.out.println(am);
		printAmerican(day, month, date, year);
		System.out.println(eur);
		printEuropean(day, month, date, year);
    } //comment
	public static void printAmerican(String day, String month, int date, int year) {
		System.out.println(day + ", " + month + " " + date + ", " + year);
	}
	public static void printEuropean(String day, String month, int date, int year) {
		System.out.println(day + " " + date + " " + month + ", " + year);
	}
}
	