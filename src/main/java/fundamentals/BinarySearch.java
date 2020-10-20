package fundamentals;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * The {@code BinarySearch} class provides a static method for binary search for an integer in a sorted array of integers.
 */
public class BinarySearch {
    /**
     * @param a the array of integers. must be sorted in ascending order
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
        Scanner scanner = new Scanner(new BufferedInputStream(System.in), "UTF-8");;

    }
}
