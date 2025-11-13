import java.util.*;

class Item {
    int weight;
    int profit;
    double ratio; // profit/weight
}

public class FractionalKnapsack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item();
            System.out.print("Enter profit and weight of item " + (i + 1) + ": ");
            items[i].profit = sc.nextInt();
            items[i].weight = sc.nextInt();
            items[i].ratio = (double) items[i].profit / items[i].weight;
        }

        System.out.print("Enter capacity of knapsack: ");
        int capacity = sc.nextInt();

        // Sort items by profit/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalProfit = 0.0;
        int remaining = capacity;

        // Greedy selection
        for (int i = 0; i < n; i++) {
            if (items[i].weight <= remaining) {
                // Take full item
                totalProfit += items[i].profit;
                remaining -= items[i].weight;
            } else {
                // Take fractional part
                totalProfit += items[i].ratio * remaining;
                break;
            }
        }

        System.out.println("\nMaximum profit = " + totalProfit);
        sc.close();
    }
}

// Total Time Complexity	O(n log n)
// Space Complexity	O(n)

// The Fractional Knapsack Problem is a Greedy Algorithm problem in which:
// you must maximize the total profit of items placed into a knapsack (bag) of limited capacity — but you can take fractions (parts) of items instead of whole items.