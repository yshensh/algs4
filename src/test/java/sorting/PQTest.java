package sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class PQTest {
    @Test
    // a client that reads transactions from file, and prints out the M largest transactions.
    public void topM() {
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
        }

        // print entries on PQ in reverse order
        Stack<Transaction> stack = new Stack<Transaction>();
        for (Transaction transaction : pq)
            stack.push(transaction);
        for (Transaction transaction : stack)
            StdOut.println(transaction);
    }

    @Test
    // a client that merges together several sorted input streams into one sorted output stream.
    public void Multiway() {
        int n = 3;
        In[] streams = new In[n];
        for (int i = 0; i < n; i++) {
            String file = String.format("src/test/resources/m%d.txt", i + 1);
            streams[i] = new In(file);
        }
        Multiway.merge(streams);
    }

}
