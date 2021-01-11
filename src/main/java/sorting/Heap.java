package sorting;

public class Heap {
    /**
     * Rearranges the array in ascending order, using the natural order.
     */
    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // heapify phase: O(nlogn)
        // reorganize the original array into a heap
        // The scan starts halfway back through the array to skip the subheaps of size 1.
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);

        // sortdown phase
        // pull the items out of the heap in decreasing order to build the sorted result
        int k = n;
        while (k > 1) {
            // remove the largest remaining item from the heap and put it into the array position vacated as the heap shrinks
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

    // Helper functions to restore the heap invariant.

    /**
     *
     * @param pq binary heap
     * @param k node index
     * @param n heap size
     */
    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            // find the larger of its two children
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            // k >= j, heap invariant holds, moving down the heap
            if (!less(pq, k, j)) break;
            // k < j, exchange the node with the larger of its two children
            exch(pq, k, j);
            // moving down the heap
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }
}
