package sorting;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    // invariant: a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while(true) {
            // scan item on left to swap
            while (less(a[++i], a[lo]))
                if (i == hi) break;

            // scan item on right to swap
            while (less(a[lo], a[--j]))
                if (j == lo) break;

            // check if pointers cross
            if (i >= j) break;

            // exchange a[i] and a[j]
            exch(a, i, j);

            // continue until pointers cross
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // return index of item now known to be in place
        return j;
    }

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
        // partition for some j:
        // 1) element a[j] is in place
        // 2) no larger element to the left of j
        // 3) no smaller element to the right of j
        int j = partition(a, lo, hi);
        // sort each piece recursively
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
}
