import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class checkPass{
  public String passW;
  public checkPass(String pass){
    retStr(pass);
  }
  
  public static String retStr(String psw) {
	  String passwordToHash = psw;
	    String generatedPassword = null;

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
	    
	    return generatedPassword;
  }
    
}