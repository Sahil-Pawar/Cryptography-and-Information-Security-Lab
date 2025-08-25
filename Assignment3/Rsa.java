package Assignment3;

import java.util.*;
public class Rsa {

    // Check if a number is prime
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // GCD function
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Modular exponentiation (fast power mod n)
    public static int modPow(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) { // if odd
                result = (result * base) % mod;
            }
            exp >>= 1; // divide exp by 2
            base = (base * base) % mod;
        }
        return result;
    }

    // Find multiplicative inverse of e mod phi (extended Euclidean algorithm)
    public static int modInverse(int e, int phi) {
        int t = 0, newT = 1;
        int r = phi, newR = e;

        while (newR != 0) {
            int quotient = r / newR;
            int tempT = t - quotient * newT;
            t = newT;
            newT = tempT;

            int tempR = r - quotient * newR;
            r = newR;
            newR = tempR;
        }

        if (r > 1) throw new ArithmeticException("e is not invertible");
        if (t < 0) t += phi;
        return t;
    }

    // RSA Algorithm
    public static void rsa(int p, int q, int plaintext) {
        int n = p * q;
        int phi = (p - 1) * (q - 1);

        // Choose e
        int e = 0;
        for (int i = 2; i < phi; i++) {
            if (gcd(i, phi) == 1) {
                e = i;
                break;
            }
        }

        // Compute d (multiplicative inverse of e mod phi)
        int d = modInverse(e, phi);

        // Encryption
        int ciphertext = modPow(plaintext, e, n);
        // Decryption
        int decrypted = modPow(ciphertext, d, n);

        System.out.println("Public key: (" + e + ", " + n + ")");
        System.out.println("Private key: (" + d + ", " + n + ")");
        System.out.println("Ciphertext: " + ciphertext);
        System.out.println("Decrypted text: " + decrypted);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first prime number (p):");
        int p = sc.nextInt();
        System.out.println("Enter second prime number (q):");
        int q = sc.nextInt();

        if (!isPrime(p) || !isPrime(q)) {
            System.out.println("Both numbers should be prime");
            return;
        }

        System.out.println("Both numbers are prime");
        System.out.println("Enter the plaintext (as integer):");
        int plaintext = sc.nextInt();

        rsa(p, q, plaintext);
    }
}
