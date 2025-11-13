import java.util.*;

public class MatrixMultiplication {

    // Single-threaded matrix multiplication
    static int[][] multiplySingleThread(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;
        int[][] C = new int[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    // Multithreaded matrix multiplication - One thread per row
    static int[][] multiplyMultiThread(int[][] A, int[][] B) {
        int m = A.length;
        int p = B[0].length;
        int[][] C = new int[m][p];

        Thread[] threads = new Thread[m];

        for (int i = 0; i < m; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < p; j++) {
                    int sum = 0;
                    for (int k = 0; k < B.length; k++) {
                        sum += A[row][k] * B[k][j];
                    }
                    C[row][j] = sum;
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < m; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return C;
    }

    // Display matrix
    static void printMatrix(int[][] M) {
        for (int[] row : M) {
            for (int val : row)
                System.out.print(val + "\t");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows of Matrix A: ");
        int m = sc.nextInt();
        System.out.print("Enter number of columns of Matrix A (and rows of Matrix B): ");
        int n = sc.nextInt();
        System.out.print("Enter number of columns of Matrix B: ");
        int p = sc.nextInt();

        int[][] A = new int[m][n];
        int[][] B = new int[n][p];

        System.out.println("Enter elements of Matrix A:");
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = sc.nextInt();

        System.out.println("Enter elements of Matrix B:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++)
                B[i][j] = sc.nextInt();

        // --- Single Thread ---
        long start1 = System.nanoTime();
        int[][] C1 = multiplySingleThread(A, B);
        long end1 = System.nanoTime();

        // --- Multi Thread ---
        long start2 = System.nanoTime();
        int[][] C2 = multiplyMultiThread(A, B);
        long end2 = System.nanoTime();

        System.out.println("\nResult (Single-threaded):");
        printMatrix(C1);

        System.out.println("\nResult (Multi-threaded):");
        printMatrix(C2);

        System.out.println("\nExecution Time Comparison (in nanoseconds):");
        System.out.println("Single-threaded: " + (end1 - start1));
        System.out.println("Multi-threaded : " + (end2 - start2));

        sc.close();
    }
}

// | Version         | Time Complexity                   | Space Complexity |
// | --------------- | --------------------------------- | ---------------- |
// | Single-threaded | O(n³)                             | O(n²)            |
// | Multi-threaded  | O(n³ / T) (T = number of threads) | O(n² + T)        |


// | Question                             | Answer                                                                    |
// | ------------------------------------ | ------------------------------------------------------------------------- |
// | What is the purpose of this project? | To compare normal and multithreaded matrix multiplication performance.    |
// | What is multithreading?              | Running multiple threads in parallel to utilize multiple CPU cores.       |
// | How is it used here?                 | Each row (or cell) of the result matrix is computed by a separate thread. |
// | Which is faster?                     | Multithreaded version for large matrices.                                 |
// | What are challenges?                 | Thread management and synchronization (using `join`).                     |
// | What is time complexity?             | O(n³) for both, but multithreaded version runs faster in practice.        |
