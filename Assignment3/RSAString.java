package Assignment3;

import java.math.BigInteger;
import java.util.Scanner;

public class RSAString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext (string): ");
        String message = sc.nextLine();

        System.out.print("Enter prime number p: ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter prime number q: ");
        BigInteger q = sc.nextBigInteger();

        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = new BigInteger("65537");
        if (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = new BigInteger("3");  // fallback
        }

        BigInteger d = e.modInverse(phi);

        System.out.println("\nPublic key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private key (d, n): (" + d + ", " + n + ")");
        System.out.println("Plaintext : " + message);

        BigInteger[] ciphertexts = new BigInteger[message.length()];
        for (int i = 0; i < message.length(); i++) {
            BigInteger plainChar = BigInteger.valueOf((int) message.charAt(i));
            ciphertexts[i] = plainChar.modPow(e, n);
        }

        System.out.print("Ciphertext: ");
        for (BigInteger c : ciphertexts) {
            System.out.print(c + " ");
        }
        System.out.println();

        StringBuilder decrypted = new StringBuilder();
        for (BigInteger c : ciphertexts) {
            BigInteger decryptedChar = c.modPow(d, n);
            decrypted.append((char) decryptedChar.intValue());
        }

        System.out.println("Decrypted : " + decrypted.toString());
    
        sc.close();
    }
}