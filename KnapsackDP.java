import java.util.Scanner;

public class KnapsackDP {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of items
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weight = new int[n];
        int[] profit = new int[n];

        // Input profit and weight for each item
        for (int i = 0; i < n; i++) {
            System.out.print("Enter profit and weight of item " + (i + 1) + ": ");
            profit[i] = sc.nextInt();
            weight[i] = sc.nextInt();
        }

        // Input capacity of knapsack
        System.out.print("Enter capacity of knapsack: ");
        int W = sc.nextInt();

        // Create DP table
        int[][] K = new int[n + 1][W + 1];

        // Build table K[][] bottom-up
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (weight[i - 1] <= w)
                    K[i][w] = Math.max(profit[i - 1] + K[i - 1][w - weight[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        System.out.println("\nMaximum profit = " + K[n][W]);

        // Optional: show items included in knapsack
        System.out.print("Items included: ");
        int res = K[n][W];
        int w = W;
        for (int i = n; i > 0 && res > 0; i--) {
            if (res != K[i - 1][w]) {
                System.out.print("Item" + i + " ");
                res = res - profit[i - 1];
                w = w - weight[i - 1];
            }
        }

        sc.close();
    }
}


// Time Complexity	O(n × W)
// Space Complexity	O(n × W)