package fundamentals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ThreeSumFast {
    public static int count(int[] a) {
        int n = a.length;
        int counter = 0;
        Arrays.sort(a);
        for (int i=0; i < n; i++)
            for (int j=i; j < n; j++) {
                int k = Arrays.binarySearch(a, -a[i]-a[j]);
                if (k > j) counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        StdOut.println(count(a));
    }
}
