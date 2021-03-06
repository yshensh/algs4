package sorting;


/**
 * The {@code Merge} class provides static methods for sorting an array using a bottom-up version of mergesort.
 *
 */
public class MergeBU {
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n-len; lo += len+len) {
                int mid  = lo+len-1;
                int hi = Math.min(lo+len+len-1, n-1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[lo..hi]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            // condition 1: left half exhausted (take from the right) - this copying is unnecessary
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
