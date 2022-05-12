import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class resetPassword{
  public String passW;
  public resetPassword(String firstName, String lastName, String cashTag, String emailNum, String card, String question, String answer,  String oldPass, String newPass){
    String passwordToHash = newPass;
    String generatedPassword = null;
    String accInfo = firstName + ":" + lastName + ":" + cashTag + ":" + emailNum + ":" + card + ":" + question + ":" + answer + ":";

    try {
      // create MessageDigest instance for MD5
      MessageDigest md = MessageDigest.getInstance("MD5");

      // add password bytes to digest
      md.update(passwordToHash.getBytes());

      // get the hash's bytes
      byte[] bytes = md.digest();

      // this bytes[] has bytes in decimal format; convert it to hexadecimal format
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }

      // get complete hashed password in hex format
      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
   
    try {
	    rewriteLine res = new rewriteLine();
	    res.rewriteLn("/Users/kathytran/eclipse-workspace/Cash App/accountCredentials.txt", oldPass, generatedPassword);
    } 
	catch(IOException e){
		e.printStackTrace();
	}
  }
}