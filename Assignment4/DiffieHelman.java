import java.util.*;

public class DiffieHelman {

    // Check if number is prime
    public boolean isPrime(int q) {
        if (q <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(q); i++) {
            if (q % i == 0)
                return false;
        }
        return true;
    }

    // Fast power mod
    public int powerMod(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            exp >>= 1;
            base = (base * base) % mod;
        }
        return result;
    }

    // Simple primitive root check
    public boolean isPrimitiveRoot(int q, int alpha) {
        Set<Integer> results = new HashSet<>();
        for (int i = 1; i < q; i++) {
            results.add(powerMod(alpha, i, q));
        }
        return results.size() == q - 1;
    }

    public static void main(String[] args) {
        DiffieHelmanEasy df = new DiffieHelmanEasy();
        Scanner input = new Scanner(System.in);

        System.out.print("Enter prime number q: ");
        int q = input.nextInt();

        if (!df.isPrime(q)) {
            System.out.println("q must be prime!");
            return;
        }

        System.out.print("Enter alpha: ");
        int alpha = input.nextInt();

        if (!df.isPrimitiveRoot(q, alpha)) {
            System.out.println(alpha + " is NOT a primitive root of " + q);
            return;
        } else {
            System.out.println(alpha + " is a valid primitive root of " + q);
        }

        System.out.print("Enter Alice's private key Xa: ");
        int Xa = input.nextInt();

        System.out.print("Enter Bob's private key Xb: ");
        int Xb = input.nextInt();

        int Ya = df.powerMod(alpha, Xa, q); // Alice's public key
        int Yb = df.powerMod(alpha, Xb, q); // Bob's public key

        int Ka = df.powerMod(Yb, Xa, q); // Shared secret
        int Kb = df.powerMod(Ya, Xb, q); // Shared secret

        System.out.println("Alice's Public Key: " + Ya);
        System.out.println("Bob's Public Key: " + Yb);
        System.out.println("Shared Secret (Ka=Kb): Ka : " + Ka + " Kb : " + Kb);
    }
}
