package sorting;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickTest {
    @Test
    public void testSort() {
        In in = new In("src/test/resources/tiny.txt");
        String[] actual = in.readAllStrings();
        String[] expected = Arrays.copyOf(actual, actual.length);
        Quick.sort(actual);
        Arrays.sort(expected);
        assertArrayEquals(expected, actual);
    }
}