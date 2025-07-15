import java.util.Scanner;

public class GaussianPrimes{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Gaussian integer form of ai+b");
        System.out.println("a:");
        int a = scanner.nextInt();
        System.out.println("b:");
        int b = scanner.nextInt();

        System.out.println("Parsed Gaussian integer: " + a + "i + " + b);

        if (isGaussianPrime(a, b)) {
            System.out.println(a + "i + " + b + " is a Gaussian prime.");
        } else {
            System.out.println(a + "i + " + b + " is NOT a Gaussian prime.");
        }
    }

    // Determines if a Gaussian integer a+bi is prime
    public static boolean isGaussianPrime(int a, int b) {
        int norm = a * a + b * b;
        if (a == 0 || b == 0) {
            int val = Math.abs(a == 0 ? b : a);
            // If either part is zero, check if absolute value is prime and ≡ 3 mod 4
            return isPrime(val) && val % 4 == 3;
        } else {
            // If both parts are nonzero, check if norm is prime
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