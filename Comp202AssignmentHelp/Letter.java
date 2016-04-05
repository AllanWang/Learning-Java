//Allan Wang	260667681

// a simple letter class
public class Letter {
	private char value;
	private boolean isGuessed;
	
	//no variables have the same name; no need for "this"
	public Letter(char c) {
		value = c;
		isGuessed = false;
	}
	
	public char getValue() {
		return value;
	}
	
	public boolean getRevealed() {
		return isGuessed;
	}
	
	public void reveal() {
		isGuessed = true;
	}
}
