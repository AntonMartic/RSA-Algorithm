# RSA-Algorithm

A clean, educational implementation of the RSA public-key cryptosystem from scratch using Java's **BigInteger** class for handling large integers. This project was developed for a Networking and Communications university assignment.

---

## 🔎 Overview

RSA (Rivest–Shamir–Adleman) is one of the first practical public-key cryptosystems and is widely used for secure data transmission. This implementation demonstrates the core mathematical principles of RSA, including key generation, encryption, and decryption, using Java's **BigInteger** library to handle the large integers required for security.

Disclaimer: This is an educational project. It is not intended for securing real-world, sensitive data. For production use, always rely on well-vetted cryptographic libraries.

---

## 🔥 Features

- Key Generation: Generates a pair of large prime numbers (p and q) to create a public/private key pair.
- Encryption: Encrypts a plaintext message using the public key (e, n).
- Decryption: Decrypts a ciphertext message back to plaintext using the private key (d, n).
- Console Demonstration: A main method that demonstrates a full workflow: key generation, encryption, and decryption.

---

## 👷‍♂️ How RSA Works

1. Key Generation:
- Choose two large distinct prime numbers, p and q.
- Compute the modulus n = p * q.
- Compute Euler's totient: φ(n) = (p-1)(q-1).
- Choose a public exponent e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1. (Often 65537).
- Compute the private exponent d, the modular multiplicative inverse of e mod φ(n). (Solves d * e ≡ 1 mod φ(n)).
  
2. Encryption: (Given public key (e, n) and message m)
- ciphertext c = mᵉ mod n
  
3. Decryption: (Given private key (d, n) and ciphertext c)
- original message m = cᵈ mod n

The security of RSA relies on the practical difficulty of factoring the product of two large prime numbers, the modulus n.

---

## ⚙️ Installation & Usage

### Prerequisites

- Java Development Kit (JDK): Version 8 or higher. (Required for BigInteger and SecureRandom classes).
- A Java IDE (like IntelliJ IDEA, Eclipse) or a command-line terminal.

### Running the Program

Clone the repository:

```bash
git clone https://github.com/your-username/rsa-java-implementation.git
cd rsa-java-implementation
```

Compile the Java file:

```bash
javac RSA.java
```

Run the program:

```bash
java RSA
```

### Example Output

Upon running, the program will execute the main method, which should produce an output similar to:

```text
Original Message: Hello, this is a secret message for Lab 1!
Encrypted Ciphertext (as number): 824329284871077893895127829871287389127389127893578912657912365987123659871236598712365987123659871...
Decrypted Message: Hello, this is a secret message for Lab 1!
SUCCESS: Decryption matched the original message!
```
