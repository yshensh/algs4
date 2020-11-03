package sorting;


/**
 * The {@code Merge} class provides static methods for sorting an array using a top-down, recursive version of mergesort.
 *
 */
public class MergeTD {
    // auxiliary array for merges
    private static Comparable[] aux;

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        // sort a[lo..hi]
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        // sort left half
        sort(a, lo, mid);
        // sort right half
        sort(a, mid+1, hi);
        // merge results
        merge(a, lo, mid, hi);
    }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        // merge a[lo..mid] with a[mid+1..hi]
        int i = lo, j = mid + 1;
        // copy a[lo..hi] to aux[lo..hi]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // merge back to a[lo..hi]
        for (int k = lo; k <= hi; k++) {
            // condition 1: left half exhausted (take from the right)
            if (i > mid) a[k] = aux[j++];

                // condition 2: right half exhausted (take from the left)
            else if (j > hi) a[k] = aux[i++];

                // condition 3: current key on the right less than current key on left (take from the right)
            else if (less(aux[j], aux[i])) a[k] = aux[j++];

                // condition 4: current key on right greater than or equal to current key on left (take from the left)
            else a[k] = aux[i++];

        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

}
