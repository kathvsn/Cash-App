import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

public class FileSearch {
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
	    
	    // return email or phone number if it exists
	    return emailOrNum;
	    
	}

}