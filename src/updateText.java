import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class updateText {
	// update pending transaction text area after a payment was made 
	public static void returnInfo(String fileName,String searchStr) throws FileNotFoundException{
		Scanner input = new Scanner(new File(fileName));
		StringBuffer buffer = new StringBuffer();

	    // loop through each line of the file
	    while (input.hasNext()) {
	        String line = input.nextLine();
	        
	        // check if this line contains user email or phone number
	        if(!(line.contains(searchStr))) {
		        buffer.append(line+System.lineSeparator());
	        }
	    }
	    String fileContents = buffer.toString();
	    input.close();
	    
	    try {
		    FileWriter writer = new FileWriter(fileName);
		    writer.append(fileContents);
		    writer.flush();
	    }
	    catch(IOException e){
	    	e.printStackTrace();
	    }
	    
	}

}