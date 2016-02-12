package projects;

public class Fibonacci {
	//comp 202 quiz 2
	public static void main(String[] args) {
		int a = 1;
		int b = 1;
		int c = 2;
		int i = 0;
		while(c < 20160215) {
			c = b + c;
			a = b;
			b = c - a;
			if (c%17 == 0) {
				System.out.println("c " + c);

				i++;
			}
		}
		System.out.println("i " + i);
	}
}
