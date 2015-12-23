 class beer {
	public static void main (String[] args) {
		beer(99);
	}
    public static void beer (int n) {
		String noBeer = "No bottles of beer on the wall, no bottles of beer, ya' can't take one down, ya' can't pass it around, 'cause there are no more bottles of beer on the wall!";
		if (n > 0) {
			yesBeer(n);
			beer(n-1);
		} else {
			System.out.println(noBeer);
			//System.out.println("");
		}
	}
	public static void yesBeer (int m) {
		String bottles, ya;
		bottles = " bottles of beer on the wall";
		ya = " bottles of beer, ya' take one down, ya' pass it around, ";
		System.out.print(m);
		System.out.print(bottles + ", ");
		System.out.print(m);
		System.out.print(ya);
		System.out.print(m-1);
		System.out.println(bottles + ".");
		System.out.println("");
		//return;
	}
}