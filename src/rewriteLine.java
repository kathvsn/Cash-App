import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class rewriteLine {
	// rewrite specific word in a line
	public static void rewriteLn(String fileName, String oldStr, String newStr) throws FileNotFoundException{
		Scanner input = new Scanner(new File(fileName));
		StringBuffer buffer = new StringBuffer();

	    // loop through each line of the file
	    while (input.hasNext()) {
	        String line = input.nextLine();
	        buffer.append(line+System.lineSeparator());

	    }
	    String fileContents = buffer.toString();
	    fileContents = fileContents.replaceAll(oldStr, newStr);
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