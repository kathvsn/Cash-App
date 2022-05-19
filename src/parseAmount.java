import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class parseAmount {
	// parse amount of money sent and received by user
	// cashtag:amount
	public static double returnInfo(String fileName,String searchStr) throws FileNotFoundException{
		Scanner input = new Scanner(new File(fileName));
		double amt = 0.0;
		String[] userInfo;

	    // loop through each line of the file
	    while (input.hasNext()) {
	        String line = input.nextLine();

	        // check if this line contains user email or phone number
	        if (line.contains(searchStr)) {
	        	userInfo =  line.split(":");
	        	amt = amt + Double.parseDouble(userInfo[1]);
	        }
	    }
	    input.close();
	    
	    // return email or phone number if it exists
	    return amt;
	    
	}

}