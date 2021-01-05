package sorting;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Quick 3-way sorting is suitable for arrays with large numbers of duplicate sort keys.
 * Illustration: https://www.cs.princeton.edu/courses/archive/fall12/cos226/demo/23DemoPartitioningDijkstra.pdf
 */
public class Quick3way {
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void sort(Comparable[] a) {
        // shuffle the array
        StdRandom.shuffle(a);
        // sort the array
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo;
        int gt = hi;
        Comparable v = a[lo];

        // Starting with i equal to lo we process a[i] using the 3-way compare given us by the Comparable interface to handle the three possible cases
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);

            // a[i] less than v: exchange a[lt] with a[i] and increment both lt and i
            if (cmp < 0) exch(a, lt++, i++);

            // a[i] greater than v: exchange a[i] with a[gt] and decrement gt
            else if (cmp > 0) exch(a, i, gt--);

            // a[i] equal to v: increment i
            else i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo,lt-1);
        sort(a,gt+1, hi);
    }
}
