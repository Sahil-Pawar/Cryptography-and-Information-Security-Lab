// Hill Cipher Implementaion
import java.util.*;
public class Hill {
    // Function to get modulo 26 of a number
    private static int mod26(int x) {
        x = x % 26;
        if (x < 0)
            x += 26;
        return x;
    }

    // Function to multiply matrices and get mod 26
    private static int[] multiply(int[][] key, int[] vector) {
        int[] result = new int[vector.length];
        for (int i = 0; i < key.length; i++) {
            result[i] = 0;
            for (int j = 0; j < vector.length; j++) {
                result[i] += key[i][j] * vector[j];
            }
            result[i] = mod26(result[i]);
        }
        return result;
    }

    // Function to find modular inverse of a number under mod 26
    private static int modInverse(int a) {
        a = mod26(a);
        for (int x = 1; x < 26; x++) {
            if (mod26(a * x) == 1)
                return x;
        }
        throw new ArithmeticException("No modular inverse exists for " + a);
    }

    // Function to get determinant of 2x2 matrix
    private static int determinant(int[][] matrix) {
        return mod26(matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
    }

    // Function to get inverse of 2x2 matrix under mod 26
    private static int[][] inverse(int[][] matrix) {
        int det = determinant(matrix);
        int detInv = modInverse(det);

        int[][] inv = new int[2][2];
        inv[0][0] = mod26(matrix[1][1] * detInv);
        inv[0][1] = mod26(-matrix[0][1] * detInv);
        inv[1][0] = mod26(-matrix[1][0] * detInv);
        inv[1][1] = mod26(matrix[0][0] * detInv);
        return inv;
    }

    // Function to convert string to vector of integers (A=0, B=1, ..., Z=25)
    private static int[] stringToVector(String text) {
        int[] vector = new int[2];
        for (int i = 0; i < 2; i++) {
            vector[i] = text.charAt(i) - 'A';
        }
        return vector;
    }

    // Function to convert vector of integers to string
    private static String vectorToString(int[] vector) {
        StringBuilder sb = new StringBuilder();
        for (int i : vector) {
            sb.append((char) (i + 'A'));
        }
        return sb.toString();
    }

    // Encrypt function
    public static String encrypt(String plaintext, int[][] key) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        if (plaintext.length() % 2 != 0)
            plaintext += "X"; // Padding

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] vector = stringToVector(plaintext.substring(i, i + 2));
            int[] result = multiply(key, vector);
            ciphertext.append(vectorToString(result));
        }
        return ciphertext.toString();
    }

    // Decrypt function
    public static String decrypt(String ciphertext, int[][] key) {
        int[][] invKey = inverse(key);
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            int[] vector = stringToVector(ciphertext.substring(i, i + 2));
            int[] result = multiply(invKey, vector);
            plaintext.append(vectorToString(result));
        }
        return plaintext.toString();
    }

    // Example usage
    public static void main(String[] args) {
        // Example 2x2 key matrix (must be invertible mod 26)
        Scanner sc = new Scanner(System.in);
        System.out.println("Hill Cipher Implementation");
        int[][] key = new int[2][2];
        System.out.println("Enter the plaintaxt:");
        String plaintext = sc.next();
        System.out.println("Enter the size of matrix:");
        int size = sc.nextInt();
        
        System.out.println("Enter 4 integers for the 2x2 key matrix (row-wise):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key[i][j] = sc.nextInt();
            }
        }

        
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);

        String decrypted = decrypt(ciphertext, key);
        System.out.println("Decrypted: " + decrypted);
    }
}