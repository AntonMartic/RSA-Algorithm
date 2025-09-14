
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
		
		byte[] messageBytes = message.getBytes();
		
		BigInteger messageBigInt = new BigInteger(1, messageBytes);
		
		// m < n, TODO: break message into blocks, each of which is less than n.
		if (messageBigInt.compareTo(mod) >= 0) {
			throw new IllegalArgumentException("Message is too long for the current key size");
		}
		
		// encrypts m (message) as c = m^e (mod n)
		return messageBigInt.modPow(key, mod);
		
	}
	
}

