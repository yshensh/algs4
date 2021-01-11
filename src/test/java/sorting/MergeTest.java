package sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeTest {
    @Test
    public void MergTDSort() {
        In in = new In("src/test/resources/tiny.txt");
        String[] actual = in.readAllStrings();
        String[] expected = Arrays.copyOf(actual, actual.length);
        MergeTD.sort(actual);
        Arrays.sort(expected);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void MergBUSort() {
        In in = new In("src/test/resources/tiny.txt");
        String[] actual = in.readAllStrings();
        String[] expected = Arrays.copyOf(actual, actual.length);
        MergeBU.sort(actual);
        Arrays.sort(expected);
        assertArrayEquals(expected, actual);
    }
}