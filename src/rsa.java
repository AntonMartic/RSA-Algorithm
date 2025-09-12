
import java.math.BigInteger;
import java.util.Random;

public class rsa {

	private BigInteger privateKey;
	private BigInteger publicKey;
	
	private int length = 64;
	
	private Random random = new Random();
	
	private void generateKeys() {
	
		BigInteger p = BigInteger.probablePrime(length, random);
		BigInteger q = BigInteger.probablePrime(length, random);
		
	    while (q.equals(p)) {
	        q = BigInteger.probablePrime(length, random);
	    }
		
		BigInteger n = p.multiply(q);
	}
}

