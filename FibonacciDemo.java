import java.util.Scanner;

public class FibonacciDemo {

    public static int recursiveFibonacci(int n) {
        if (n <= 1)
            return n;
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    public static int iterativeFibonacci(int n) {
        if (n <= 1)
            return n;

        int a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number (n): ");
        int n = sc.nextInt();

        // --- Recursive approach ---
        long startRecursive = System.nanoTime();
        int fibRec = recursiveFibonacci(n);
        long endRecursive = System.nanoTime();

        // --- Iterative approach ---
        long startIterative = System.nanoTime();
        int fibIter = iterativeFibonacci(n);
        long endIterative = System.nanoTime();

        // --- Display results ---
        System.out.println("\n=== Fibonacci Calculation for n = " + n + " ===");
        System.out.println("Recursive Result: " + fibRec);
        System.out.println("Iterative Result: " + fibIter);

        System.out.println("\n--- Execution Time (in nanoseconds) ---");
        System.out.println("Recursive: " + (endRecursive - startRecursive));
        System.out.println("Iterative: " + (endIterative - startIterative));

        // --- Complexity Summary ---
        System.out.println("\n=== Time & Space Complexity Analysis ===");
        System.out.println("Recursive:  Time = O(2^n),  Space = O(n)");
        System.out.println("Iterative:  Time = O(n),    Space = O(1)");

        sc.close();
    }
}

// Each Fibonacci number is the sum of the two previous ones
// Time Complexity = O(2ⁿ)   Space Complexity = O(n)