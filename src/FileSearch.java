import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSearch {
	// search file to see if user exists
	public static String returnInfo(String fileName,String searchStr) throws FileNotFoundException{
		Scanner input = new Scanner(new File(fileName));
		String emailOrNum = "";

	    // loop through each line of the file
	    while (input.hasNext()) {
	        String line = input.nextLine();

	        // check if this line contains user email or phone number
	        if (line.contains(searchStr)) {
	            emailOrNum = line;
	            break;
	        }
	    }
	    input.close();
	    
	    // return email or phone number if it exists
	    return emailOrNum;
	    
	}

}