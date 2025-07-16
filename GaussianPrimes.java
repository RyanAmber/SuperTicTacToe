import java.util.Scanner;

public class GaussianPrimes{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter a Gaussian integer form of ai+b");
        //System.out.println("a:");
        //int a = scanner.nextInt();
        System.out.println("Max Distance:");
        int max = scanner.nextInt();
        for (int k=1;k<=b;k++){
            int primes=0;
        //int total=0;
        for (int i=-k; i <= k; i++) {
            for (int j=-k; j <= k; j++) {
                //total++;
                if (isGaussianPrime(i, j)) {
                    //System.out.println("Gaussian prime: " + i + "i + " + j);
                    primes++;
                }
            }
        }
        //System.out.println("Total Gaussian integers checked from " + -a + "i + " + -b + " to " + a + "i + " + b + ": " + total);
        //System.out.println("Total Gaussian primes found: " + primes);
        System.out.println(primes);
        }
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