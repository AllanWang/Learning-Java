package projects;

import java.util.Arrays;

public class Short {
    
	public static void main (String[] args) throws java.lang.Exception
	{
		int store = 231;
		int storeLength = String.valueOf(store).length();
 
		short[] storeRepeat = new short [storeLength];
		for(int k = 0; k < storeLength; k++){
			storeRepeat[k] = (short) (store % 10);
			store = store / 10;
			
		}	
		 
		System.out.println(Arrays.toString(storeRepeat));	}
}
