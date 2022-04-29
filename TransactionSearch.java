import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;  

public class TransactionSearch {
	public static Vector<String> returnInfo(String fileName,String searchStr, boolean checkPayNum, boolean checkReqNum) throws FileNotFoundException{
		Scanner input = new Scanner(new File(fileName));
		Vector<String> pendingTrans= new Vector<>();
		Vector<String> payNum = new Vector<>();
		Vector<String> reqNum = new Vector<>();

	    // loop through each line of the file
	    while (input.hasNext()) {
	        String line = input.nextLine();

	        // check if this line contains user email or phone number
	        if(checkPayNum) {
	        	String p = searchStr + "_PAYMENT";
	        	if (line.contains(p)) {
		            payNum.add(line);
		        }
	        }
	        else if(checkReqNum) {
	        	String r = searchStr + "_REQUEST";
	        	if (line.contains(r)) {
		            reqNum.add(line);
		        }
	        }
	        else if(line.contains(searchStr)) {
	            pendingTrans.add(line);
	        }
	    }
	    
	    // return vector of strings
	    if(checkPayNum) {
	    	return payNum;
	    }
	    else if(checkReqNum) {
	    	return reqNum;
	    }
	    else {
	    	return pendingTrans;
	    }
	    
	}

}