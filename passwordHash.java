import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class passwordHash{
  public passwordHash(String firstName, String lastName, String cashTag, String emailNum, String pass){
    String passwordToHash = pass;
    String generatedPassword = null;
    String accInfo = firstName + "~" + lastName + "~" + cashTag + "~" + emailNum + "~";

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
    // write credentials + hashed password to file
    accInfo = accInfo + generatedPassword;
    fileIO fio = new fileIO("accountCredentials.txt");
	fio.wrData(accInfo);
  }
}