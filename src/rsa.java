
import java.math.BigInteger;
import java.security.SecureRandom;

/*
 * RSA Algorithm
 * 
 * 1. Bob chooses secret primes p and q and computes n = pq.
 * 2. Bob chooses e with gcd(e, (p - 1)(q - 1)) = 1.
 * 3. Bob computes d with de = 1 (mod (p - l)(q - 1)).
 * 4. Bob makes n and e public, and keeps p, q, d secret.
 * 5. Alice encrypts m as c = m^e (mod n) and sends c to Bob.
 * 6. Bob decrypts by computing m = c^d (mod n).
 * 
 */

public class rsa {

	private BigInteger privateKey;
	
	private BigInteger publicKey;
	
	private BigInteger modulus;
	
	// TODO: factor the modulus n with a simple online tool to break the encryption with lower bit like 16, 32, 64 
	private int bitLength = 1024; 
	
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
		
		// Ensure e is coprime with φ(n) (gcd(e, φ(n)) == 1)
		while (!phiN.gcd(publicKey).equals(BigInteger.ONE)) {
	        publicKey = publicKey.add(BigInteger.ONE);
	    }

		// 'd': d = e^(-1) mod φ(n), division = multiplicate inverse modulus ...
		privateKey = publicKey.modInverse(phiN);
		
	}
	
	public BigInteger getPublicKey() {
	    return publicKey;
	}
	
	public BigInteger getPrivateKey() {
	    return privateKey;
	}
	
	public BigInteger getModulus() {
	    return modulus;
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

