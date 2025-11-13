import java.util.*;

class Node {
    char ch;
    int freq;
    Node left, right;
}

class CompareFreq implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return a.freq - b.freq;  // sort ascending by frequency
    }
}

public class SimpleHuffman {

    // Function to print Huffman Codes
    static void printCode(Node root, String code) {
        if (root == null)
            return;

        // If leaf node → print code
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + code);
            return;
        }

        // Recursive calls
        printCode(root.left, code + "0");
        printCode(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        char[] chars = new char[n];
        int[] freq = new int[n];

        System.out.println("Enter characters and their frequencies:");
        for (int i = 0; i < n; i++) {
            System.out.print("Character " + (i + 1) + ": ");
            chars[i] = sc.next().charAt(0);
            System.out.print("Frequency of " + chars[i] + ": ");
            freq[i] = sc.nextInt();
        }

        // Priority queue (min-heap)
        PriorityQueue<Node> pq = new PriorityQueue<>(n, new CompareFreq());

        // Create leaf nodes
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            node.ch = chars[i];
            node.freq = freq[i];
            pq.add(node);
        }

        // Build Huffman Tree (Greedy step)
        while (pq.size() > 1) {
            Node a = pq.poll();  // smallest freq
            Node b = pq.poll();  // next smallest

            Node parent = new Node();
            parent.ch = '-';  // internal node
            parent.freq = a.freq + b.freq;
            parent.left = a;
            parent.right = b;

            pq.add(parent);
        }

        // Root of Huffman Tree
        Node root = pq.poll();

        System.out.println("\nHuffman Codes:");
        printCode(root, "");

        sc.close();
    }
}


// A compression algorithm assigning shorter codes to frequent characters.
// A lossless data compression algorithm using variable-length binary codes.

// Time Complexity: O(n log n)
// Space Complexity: O(n)