package sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

import java.util.Stack;

public class PQTest {
    public static void main(String[] args) {

        int m = 3;  // top 3 records
        MinPQ<Transaction> pq = new MinPQ<>(m+1);

        In in = new In("src/test/resources/tinyBatch.txt");
        while (!in.isEmpty()) {
            String line = in.readLine();
            Transaction transaction = new Transaction(line);
            pq.insert(transaction);

            // remove minimum if m+1 entries on the PQ
            if (pq.size() > m) {
                pq.delMin();
            }
            StdOut.println(pq.size());
        }

        StdOut.println(pq.size());

        // print entries on PQ in reverse order
        Stack<Transaction> stack = new Stack<Transaction>();
        for (Transaction transaction : pq)
            stack.push(transaction);
        for (Transaction transaction : stack)
            StdOut.println(transaction);
    }
}
