package sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * The Multiway class provides a client for reading in several sorted text files and
 * merging them together into a single sorted text stream.
 *
 */
public class Multiway {
    // merge together the sorted input streams and write the sorted result to standard output
    public static void merge(In[] streams) {
        int n = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<String>(n);
        /*
         * Initialization: each stream index is associated with a key (the next string in
         * the stream)
         */
        for (int i = 0; i < n; i++)
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());
        /*
         * After initialization, it enters a loop that prints the smallest string in the queue
         * and removes the corresponding entry, then adds a new entry for the next string in
         * that stream
         */
        // Extract and print min and read next from its stream.
        while (!pq.isEmpty()) {
            StdOut.print(pq.minKey() + " ");
            int i = pq.delMin();
            if (!streams[i].isEmpty())
                pq.insert(i, streams[i].readString());
        }
        StdOut.println();
    }
}
