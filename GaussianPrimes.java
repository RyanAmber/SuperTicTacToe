import java.util.Scanner;

public class GaussianPrimes{
    public static void main(String[] args)
{
    Scanner scanner = new Scanner(System.in);
    System.out.println("Max Norm:");
    int maxNorm = scanner.nextInt();
    int primes = 0;
    int limit = (int)Math.ceil(Math.sqrt(maxNorm));
    for (int a = -limit; a <= limit; a++) {
        for (int b = -limit; b <= limit; b++) {
            int norm = a * a + b * b;
            if (norm <= maxNorm && isGaussianPrime(a, b)) {
                primes++;
            }
        }
    }
    System.out.println(primes);
}

    // Determines if a Gaussian integer a+bi is prime
    public static boolean isGaussianPrime(int a, int b) {
        int norm = a * a + b * b;
        if (a == 0 || b == 0) {
            int val = Math.abs(a == 0 ? b : a);
            return isPrime(val) && val % 4 == 3;
        } else {
            return isPrime(norm);
        }
    }

    // Simple primality check
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}