
import java.math.BigInteger;

public class main {

	public static void main(String[] args) {
		
		rsa rsa = new rsa();
		
		// Message
	    String originalMessage = "Secret message";
	    System.out.println("Original Message: " + originalMessage);
	    
	    // Encrypt message with public key
	    BigInteger ciphertext = rsa.encrypt(originalMessage, rsa.getPublicKey(), rsa.getModulus());
	    System.out.println("Encrypted Ciphertext (as number): " + ciphertext);
	    
	    // Decrypt message with private key
	    String decryptedMessage = rsa.decrypt(ciphertext, rsa.getPrivateKey(), rsa.getModulus());
	    System.out.println("Decrypted Message: " + decryptedMessage);
	    
	    // Verify it worked
	    if (originalMessage.equals(decryptedMessage)) {
	        System.out.println("SUCCESS: Decryption matched the original message!");
	    } else {
	    	System.out.println("ERROR: Something went wrong!");
	    }

	}

}
