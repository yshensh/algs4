package fundamentals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class TwoSumFast {
    public static int count(int[] a) {
        int n = a.length;
        int counter = 0;
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            int j = Arrays.binarySearch(a, -a[i]);
            if (j > i) counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        StdOut.println(count(a));
    }
}
