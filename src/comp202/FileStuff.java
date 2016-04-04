package comp202;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileStuff {

	public static void main (String[] args) {
		FileWriter fw;
		try {
			fw = new FileWriter("file.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			String message = "Hello!\n";
			bw.write(message);
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
