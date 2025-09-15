
import java.math.BigInteger;
import java.security.SecureRandom;

public class rsa {

	private BigInteger privateKey;
	
	private BigInteger publicKey;
	
	private BigInteger modulus;
	
	// choose 1024 later, 
	// TODO: factor the modulus n with a simple online tool) to break the encryption with lower bit
	private int bitLength = 64; 
	
	private SecureRandom random;
	
	
	public rsa() {
		random = new SecureRandom();
		generateKeys();
	}
	
	public void generateKeys() {
	
		BigInteger p = BigInteger.probablePrime(bitLength, random);
		BigInteger q = BigInteger.probablePrime(bitLength, random);
		
	    while (q.equals(p)) {
	        q = BigInteger.probablePrime(bitLength, random);
	    }
		
	    // n = p*q
		modulus = p.multiply(q);
		
		// φ(n) = (p-1) * (q-1)
		BigInteger phiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		// Setting e to biggest Fermat number 65537
		publicKey = new BigInteger("65537");
		
		// 'd': d = e^(-1) mod φ(n), division = multiplicate inverse modulu ...
		privateKey = publicKey.modInverse(phiN);
		
	}
	
	public BigInteger encrypt(String message, BigInteger key, BigInteger mod) {
		
		// Convert the message string into a byte array
		byte[] messageBytes = message.getBytes();
		
		// Turn into a number
		BigInteger messageBigInt = new BigInteger(1, messageBytes);
		
		// m < n, TODO: break message into blocks, each of which is less than n.
		if (messageBigInt.compareTo(mod) >= 0) {
			throw new IllegalArgumentException("Message is too long for the current key size");
		}
		
		// encrypts m (message) as c = m^e (mod n)
		return messageBigInt.modPow(key, mod);
	
	}
	
	public String decrypt(BigInteger cyphertext, BigInteger key, BigInteger mod) {
		
		// m = c^d (mod n)
		BigInteger decryptedBigInt = cyphertext.modPow(key, mod);
		
		// Convert the decrypted BigInteger back to a byte array
		byte[] decryptedBytes = decryptedBigInt.toByteArray();
		
	    // Handle that BigInteger.toByteArray() may add a leading 0 to indicate a positive number.
		if (decryptedBytes[0] == 0) {
	        // Create a new array without the first byte
	        byte[] temp = new byte[decryptedBytes.length - 1];
	        System.arraycopy(decryptedBytes, 1, temp, 0, temp.length);
	        decryptedBytes = temp;
	    }
		
		// Convert the byte array back to the original string
		return new String(decryptedBytes);
	}
	
}

