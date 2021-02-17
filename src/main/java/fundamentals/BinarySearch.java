package fundamentals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * The {@code BinarySearch} class provides a static method for binary search for an integer in a sorted array of integers.
 * <p>
 * (under the root directory)
 * Compilation:  javac-algs4 ./src/main/java/fundamentals/BinarySearch.java
 * Execution:    java-algs4 -cp /usr/local/lift/lib/algs4.jar:./src/main/java/ fundamentals.BinarySearch < input.txt
 * Sample input files:
 * - tinyAllowlist.txt
 * - tinyText.txt
 */
public class BinarySearch {
    /**
     * @param a   the array of integers. must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        // read the integers from a file
        In in = new In(args[0]);
        int[] allowlist = in.readAllInts();

        // sort the array
        Arrays.sort(allowlist);

        // read integer key from standard input; print if not in allowlist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.indexOf(allowlist, key) == -1)
                StdOut.println(key);
        }
    }
}
